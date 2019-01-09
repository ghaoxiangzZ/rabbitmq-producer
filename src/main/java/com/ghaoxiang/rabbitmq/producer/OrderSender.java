/*
* 文件名: com.ghaoxiang.rabbitmq.producer
* 文件编号: 
* 版权: Copyright (c) 2019, YAN Co.Ltd. and/or its affiliates. All rights reserved.Use is subject to license terms.
* 描述: 
* 创建人: ghaoxiang
* 创建时间: 2019年01月09日 09:59
* 修改人:
* 修改时间: 2019年01月09日 09:59
* 修改变更号: 
* 修改内容: 
*/
package com.ghaoxiang.rabbitmq.producer;

import java.util.Date;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ghaoxiang.rabbitmq.Constants.Constants;
import com.ghaoxiang.rabbitmq.dao.BrokerMessageLogMapper;
import com.ghaoxiang.rabbitmq.entity.Order;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title OrderSender
 * @Description
 * @date 2019年01月09日 09:59
 * @since V1.0
 */
@Component
public class OrderSender {

    /**
     * 自动注入RabbitTemplate模板类
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    // 异步监听broker回调函数
    /*final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if(ack){
                // 如果confirm返回成功 则进行更新
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
            } else {
                // 失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        }
    };*/

    // 异步监听broker回调函数(lambda)
    final RabbitTemplate.ConfirmCallback confirmCallback = ((correlationData, ack, cause) -> {
        System.err.println("correlationData: " + correlationData);
        if (ack) {
            brokerMessageLogMapper.changeBrokerMessageLogStatus(correlationData.getId(), Constants.ORDER_SEND_SUCCESS, new Date());
        } else {
            System.err.println("异常处理...");
        }
    });


    // 消息发送rabbitmq
    public void sendOrder(Order order) throws Exception {
        // 通过实现 ConfirmCallback 接口，消息发送到Broker后触发回调，确认消息是否到达Broker服务器，也就是只确认是否正确到达Exchange(交换机)中
        rabbitTemplate.setConfirmCallback(confirmCallback);
        // 消息唯一ID(一般设置为业务唯一主键)
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange", "order.ABC", order, correlationData);
    }
}
