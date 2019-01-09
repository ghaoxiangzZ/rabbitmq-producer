/*
* 文件名: com.ghaoxiang.rabbitmq.service
* 文件编号: 
* 版权: Copyright (c) 2019, YAN Co.Ltd. and/or its affiliates. All rights reserved.Use is subject to license terms.
* 描述: 
* 创建人: ghaoxiang
* 创建时间: 2019年01月09日 09:57
* 修改人:
* 修改时间: 2019年01月09日 09:57
* 修改变更号: 
* 修改内容: 
*/
package com.ghaoxiang.rabbitmq.service;

import com.ghaoxiang.rabbitmq.entity.Order;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title OrderService
 * @Description
 * @date 2019年01月09日 09:57
 * @since V1.0
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     * @throws Exception
     */
    void createOrder(Order order) throws Exception;
}
