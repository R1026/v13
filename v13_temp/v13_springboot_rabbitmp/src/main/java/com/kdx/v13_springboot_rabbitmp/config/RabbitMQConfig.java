package com.kdx.v13_springboot_rabbitmp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Configuration
public class RabbitMQConfig {

    //声明队列
    @Bean
    public Queue getQueue(){
        return new Queue("sbq");
    }
}
