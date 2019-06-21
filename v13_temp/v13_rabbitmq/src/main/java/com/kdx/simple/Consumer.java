package com.kdx.simple;


import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/20
 */
public class Consumer {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getCoonnection();
        Channel channel = connection.createChannel();
        //消费者监听队列
        channel.basicConsume("simple_queue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body);
                System.out.println("接收到消息队列的信息:"+str);
            }
        });
    }
}
