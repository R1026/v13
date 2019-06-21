package com.kdx.v13_search_web.config;

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
    //声明队列
    @Bean
    public Queue initQueue(){
        return new Queue(RabbitMQConstant.SOLR_ADD_MESSAGE_QUEUE);
    }

    //声明交换机,如果交换机存在，则不会做操作
    @Bean
    public TopicExchange initTopicExchange(){
        return new TopicExchange(RabbitMQConstant.PRODUCT_CENTER_EXCHANGE);
    }

    //绑定交换机
    @Bean
    public Binding initBinding(Queue initQueue,TopicExchange initTopicExchange){

        return BindingBuilder.bind(initQueue).to(initTopicExchange).with("product.add");
    }
}
