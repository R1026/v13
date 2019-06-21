package com.kdx.v13_springboot_rabbitmp.provider;

import com.kdx.v13_springboot_rabbitmp.pojo.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Component
public class Provider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("","sbq","声明队列，springboot整合rabbitmq成功。");
    }


    public void sendBook(){
        Book book = new Book("终生学习","kkk",49);
        rabbitTemplate.convertAndSend("","sbq",book);
    }
}
