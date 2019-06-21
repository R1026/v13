package com.kdx.work;


import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/20
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getCoonnection();
        final Channel channel = connection.createChannel();

        channel.basicQos(1);
        //消费者监听队列
        //true/false:是否自动回复服务器
        channel.basicConsume("work_queue",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body);
                System.out.println("接收到消息队列的信息:"+str);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //true/false:是否批量确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
