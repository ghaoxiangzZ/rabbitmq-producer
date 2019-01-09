package com.ghaoxiang.rabbitmq;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ghaoxiang.rabbitmq.entity.Order;
import com.ghaoxiang.rabbitmq.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private OrderService orderService;

	@Test
	public void testSend() throws Exception {
		Order order = new Order();
		order.setId(20190109);
		order.setName("测试订单2019-01-09");
		order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
		orderService.createOrder(order);
	}
}

