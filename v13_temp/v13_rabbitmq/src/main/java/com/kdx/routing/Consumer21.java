package com.kdx.routing;

import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/20
 */
public class Consumer21 {

    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getCoonnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("topic_queue1",false,false,false,null);
        channel.queueBind("topic_queue1","all","product.*");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body);
                System.out.println("消费者1接受到队列消息:"+str);
            }
        };
        channel.basicConsume("topic_queue1",true,consumer);
    }
}
