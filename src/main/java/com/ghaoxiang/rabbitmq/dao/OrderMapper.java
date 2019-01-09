package com.ghaoxiang.rabbitmq.dao;


import org.apache.ibatis.annotations.Mapper;

import com.ghaoxiang.rabbitmq.entity.Order;

/**
 * @author ghaoxiang
 * @version V1.0
 * @date 2019年01月09日 09:43
 * @since V1.0
 */
@Mapper
public interface OrderMapper {

    /**
     * 新增* @author ghaoxiang

     * @param record 订单实体
     * @date 2019年01月09日 09:43
     * @return int
     */
    int insert(Order record);

    /**
     * 根据主键删除
     * @author ghaoxiang
     * @param id 订单id
     * @date 2019年01月09日 09:43
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据传入信息新增
     * @author ghaoxiang
     * @param record 订单实体
     * @date 2019年01月09日 09:43
     * @return int
     */
    int insertSelective(Order record);

    /**
     * 根据主键查询
     * @author ghaoxiang
     * @param id 订单id
     * @date 2019年01月09日 09:43
     * @return Order
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * 根据主键以及传入的值更新
     * @author ghaoxiang
     * @param record 订单实体
     * @date 2019年01月09日 09:43
     * @return int
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 根据主键更新
     * @author ghaoxiang
     * @param record 订单实体
     * @date 2019年01月09日 09:43
     * @return int
     */
    int updateByPrimaryKey(Order record);
}
