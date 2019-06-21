package com.kdx.v13_item_web.config;

import com.kdx.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue initQueue(){
        return new Queue(RabbitMQConstant.ITEM_QUEUE);
    }

    @Bean
    public TopicExchange initExchange(){
        return new TopicExchange(RabbitMQConstant.PRODUCT_CENTER_EXCHANGE);
    }

    @Bean
    public Binding initBinding(Queue initQueue,TopicExchange initExchange){
        return BindingBuilder.bind(initQueue).to(initExchange).with("add_item");
    }
}
