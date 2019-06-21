package com.kdx.v13_springboot_rabbitmp.consumer;

import com.kdx.v13_springboot_rabbitmp.pojo.Book;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kdx
 * @Date 2019/6/21
 */
@Component
@RabbitListener(queues = "sbq")
public class Consumer {

    /*@RabbitHandler
    public void process(String message){
        System.out.println(message);

    }*/

    @RabbitHandler
    public void process1(Book book){
        System.out.println(book.getName());
    }
}
