package com.kdx.simple;

import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author kdx
 * @Date 2019/6/20
 */
public class Provider {


    public static void main(String[] args) throws IOException {

        Connection coonnection = ConnectionUtil.getCoonnection();

        Channel channel = coonnection.createChannel();
        channel.queueDeclare("simple_queue",false,false,false,null);
        String str = "rabbitmq简单消息队列";
        channel.basicPublish("","simple_queue",null,str.getBytes());
        System.out.println("向队列中发送消息成功!");
    }
}
