package com.kdx.work;

import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.*;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/20
 */
public class Consumer2 {
    /*public static void main(String[] args) throws IOException {
        *//*
            默认会是轮询的方式调用队列中的消息
         *//*
        Connection connection = ConnectionUtil.getCoonnection();
        Channel channel = connection.createChannel();
        //消费者监听队列
        channel.basicConsume("work_queue",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body);
                System.out.println("接收到消息队列的信息:"+str);
            }
        });
    }*/

    /*
        改进，让处理能力强的消费者处理更多消息
     */
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getCoonnection();
        final Channel channel = connection.createChannel();
        //表示一次最多给队列放一个消息，处理完毕后再放第二个消息
        channel.basicQos(1);
        channel.basicConsume("work_queue",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body);
                System.out.println("收到队列消息:"+str);
                //手工回复消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }

}
