/*
* 文件名: com.ghaoxiang.rabbitmq.service.impl
* 文件编号: 
* 版权: Copyright (c) 2019, YAN Co.Ltd. and/or its affiliates. All rights reserved.Use is subject to license terms.
* 描述: 
* 创建人: ghaoxiang
* 创建时间: 2019年01月09日 09:58
* 修改人:
* 修改时间: 2019年01月09日 09:58
* 修改变更号: 
* 修改内容: 
*/
package com.ghaoxiang.rabbitmq.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghaoxiang.rabbitmq.Constants.Constants;
import com.ghaoxiang.rabbitmq.dao.BrokerMessageLogMapper;
import com.ghaoxiang.rabbitmq.dao.OrderMapper;
import com.ghaoxiang.rabbitmq.entity.BrokerMessageLog;
import com.ghaoxiang.rabbitmq.entity.Order;
import com.ghaoxiang.rabbitmq.producer.OrderSender;
import com.ghaoxiang.rabbitmq.service.OrderService;
import com.ghaoxiang.rabbitmq.util.DateUtils;
import com.ghaoxiang.rabbitmq.util.FastJsonConvertUtil;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title OrderServiceImpl
 * @Description
 * @date 2019年01月09日 09:58
 * @since V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private OrderSender orderSender;

    @Override
    public void createOrder(Order order) throws Exception {
        // 业务数据入库
        Date orderTime = new Date();
        orderMapper.insert(order);
        // 插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        // 消息唯一ID
        brokerMessageLog.setMessageId(order.getMessageId());
        // 保存消息整体 转为JSON 格式存储入库
        brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(order));
        // 设置消息状态为0 表示发送中
        brokerMessageLog.setStatus("0");
        // 设置消息未确认超时时间窗口为 一分钟
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogMapper.insertSelective(brokerMessageLog);
        // 业务数据发送rabbitmq
        orderSender.sendOrder(order);
    }
}
