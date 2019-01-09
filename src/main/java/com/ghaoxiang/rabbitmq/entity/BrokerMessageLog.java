package com.ghaoxiang.rabbitmq.entity;

import java.util.Date;

/**
 * @author ghaoxiang
 * @version V1.0
 * @Title BrokerMessageLog
 * @Description
 * @date 2019年01月09日 09:43
 * @since V1.0
 */
public class BrokerMessageLog {

    /**
     * 唯一标识
     */
    private String messageId;

    /**
     * JSON报文
     */
    private String message;

    /**
     * 尝试请求次数
     */
    private Integer tryCount;

    /**
     * 状态 0-处理中 1-处理成功 2-处理失败
     */
    private String status;

    /**
     * 下次尝试时间
     */
    private Date nextRetry;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Date nextRetry) {
        this.nextRetry = nextRetry;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

