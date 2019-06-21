package com.kdx.v13_springboot_rabbitmp;

import com.kdx.v13_springboot_rabbitmp.provider.Provider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13SpringbootRabbitmpApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Provider provider;

	@Test
	public void contextLoads() {
		rabbitTemplate.convertAndSend("","simple_queue","springboot整合rabbitmq");
		rabbitTemplate.convertAndSend("publish","","发送信息至交换机...");
		System.out.println("消息发送完成!");
	}


	@Test
	public void methodTest(){

		//provider.send();
		provider.sendBook();
	}

}
