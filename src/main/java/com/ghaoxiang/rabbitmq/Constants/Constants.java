/*
* 文件名: com.ghaoxiang.rabbitmq.Constants
* 文件编号: 
* 版权: Copyright (c) 2019, YAN Co.Ltd. and/or its affiliates. All rights reserved.Use is subject to license terms.
* 描述: 
* 创建人: ghaoxiang
* 创建时间: 2019年01月09日 10:01
* 修改人:
* 修改时间: 2019年01月09日 10:01
* 修改变更号: 
* 修改内容: 
*/
package com.ghaoxiang.rabbitmq.Constants;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title Constants
 * @Description
 * @date 2019年01月09日 10:01
 * @since V1.0
 */
public class Constants {

    /**
     * 发送中
     */
    public static final String ORDER_SENDING = "0";

    /**
     * 发送成功
     */
    public static final String ORDER_SEND_SUCCESS = "1";

    /**
     * 发送失败
     */
    public static final String ORDER_SEND_FAILURE = "2";

    /**
     * 分钟超时单位：min
     */
    public static final int ORDER_TIMEOUT = 1;
}
