package com.kdx.v13_center_web.confing;

import com.kdx.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Configuration
public class RabbitMQConfig {

    //声明交换机
    @Bean
    public TopicExchange initTopicExchange(){

        return new TopicExchange(RabbitMQConstant.PRODUCT_CENTER_EXCHANGE);
    }
}
