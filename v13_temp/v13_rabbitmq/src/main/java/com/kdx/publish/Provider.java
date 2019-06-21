package com.kdx.publish;

import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 发布订阅模式
 * @author kdx
 * @Date 2019/6/20
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        Connection conn = ConnectionUtil.getCoonnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("publish","fanout");

        String str = "发布订阅模式----》交换机";
        channel.basicPublish("publish","",null,str.getBytes());
        System.out.println("信息发布成功!");
    }
}
