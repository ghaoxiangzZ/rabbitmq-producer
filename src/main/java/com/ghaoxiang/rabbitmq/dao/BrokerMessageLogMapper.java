package com.ghaoxiang.rabbitmq.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ghaoxiang.rabbitmq.entity.BrokerMessageLog;

/**
 * @author ghaoxiang
 * @version V1.0
 * @date 2019年01月09日 09:43
 * @since V1.0
 */
@Mapper
public interface BrokerMessageLogMapper {

    /**
     * 查询消息状态为0(发送中) 且已经超时的消息集合
     * @return List<BrokerMessageLog>
     */
    List<BrokerMessageLog> query4StatusAndTimeoutMessage();

    /**
     * 重新发送统计count发送次数 +1
     * @param messageId 唯一标识
     * @param updateTime 更新时间
     */
    void update4ReSend(@Param("messageId") String messageId, @Param("updateTime") Date updateTime);

    /**
     * 更新最终消息发送结果 成功 or 失败
     * @param messageId 唯一标识
     * @param status 状态
     * @param updateTime 更新时间
     */
    void changeBrokerMessageLogStatus(@Param("messageId") String messageId, @Param("status") String status, @Param("updateTime") Date updateTime);

    /**
     * 新增
     * @param record
     * @return int
     */
    int insertSelective(BrokerMessageLog record);
}

