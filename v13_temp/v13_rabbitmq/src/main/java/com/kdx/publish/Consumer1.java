package com.kdx.publish;

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
        Channel channel = connection.createChannel();
        channel.queueDeclare("publish_queue1",false,false,false,null);
        channel.queueBind("publish_queue1","publish","");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String str = new String(body);
                System.out.println("消费者1接受到队列消息:"+str);
            }
        };
        channel.basicConsume("publish_queue1",true,consumer);
    }
}
