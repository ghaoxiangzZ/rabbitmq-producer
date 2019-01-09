/*
* 文件名: com.ghaoxiang.rabbitmq.task
* 文件编号: 
* 版权: Copyright (c) 2019, YAN Co.Ltd. and/or its affiliates. All rights reserved.Use is subject to license terms.
* 描述: 
* 创建人: ghaoxiang
* 创建时间: 2019年01月09日 10:25
* 修改人:
* 修改时间: 2019年01月09日 10:25
* 修改变更号: 
* 修改内容: 
*/
package com.ghaoxiang.rabbitmq.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ghaoxiang.rabbitmq.Constants.Constants;
import com.ghaoxiang.rabbitmq.dao.BrokerMessageLogMapper;
import com.ghaoxiang.rabbitmq.entity.BrokerMessageLog;
import com.ghaoxiang.rabbitmq.entity.Order;
import com.ghaoxiang.rabbitmq.producer.OrderSender;
import com.ghaoxiang.rabbitmq.util.FastJsonConvertUtil;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title RetryMessageTasker
 * @Description
 * @date 2019年01月09日 10:25
 * @since V1.0
 */
@Component
public class RetryMessageTasker {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    // 定时任务，初始延迟5s执行，每10s执行一次
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void reSend(){
        System.out.println("-----------定时任务开始-----------");
        // 查询表broker_message_log，找到status为0-处理中并已超时的数据
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        list.forEach(messageLog -> {
            if(messageLog.getTryCount() >= 3){
                // 尝试次数大于等于3则判定为失败，置状态为2-处理失败
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(),
                        Constants.ORDER_SEND_FAILURE, new Date());
            } else {
                // 尝试次数小于3次，将尝试次数自增1并再次请求rabbitmq broker
                brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(), new Date());
                Order reSendOrder = FastJsonConvertUtil.convertJSONToObject(messageLog.getMessage(), Order.class);
                try {
                    orderSender.sendOrder(reSendOrder);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("-----------异常处理-----------");
                }
            }
        });
    }
}
