package com.kdx.work;

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
        channel.queueDeclare("work_queue",false,false,false,null);
        for (int i = 1; i < 11; i++) {
            String str = "第"+i+"次尝试...";
            channel.basicPublish("","work_queue",null,str.getBytes());
            System.out.println("向队列中发送消息第"+i+"次成功!");
        }

    }
}
