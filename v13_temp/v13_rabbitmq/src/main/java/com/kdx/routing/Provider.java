package com.kdx.routing;

import com.kdx.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 路由模式
 * @author kdx
 * @Date 2019/6/20
 */
public class Provider {


    /*public static void main(String[] args) throws IOException {
        Connection conn = ConnectionUtil.getCoonnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("routing","direct");

        String str1 = "路由模式模式---->视情况发布消息给队列(猛龙总冠军)";
        String str2 = "cba:广东九连冠!!!";
        channel.basicPublish("routing","nba",null,str1.getBytes());
        channel.basicPublish("routing","cba",null,str2.getBytes());

        System.out.println("信息发布成功!");
    }*/
    /*
        通配符的方式
     */
    public static void main(String[] args) throws IOException {
        Connection conn = ConnectionUtil.getCoonnection();
        Channel channel = conn.createChannel();

        channel.exchangeDeclare("all","topic");

        String str1 = "收到信息请添加商品信息入库";
        String str2 = "请管理员修改商品信息";
        channel.basicPublish("all","product.add",null,str1.getBytes());
        channel.basicPublish("all","product.updata.del",null,str2.getBytes());

        System.out.println("信息发布成功!");
    }
}
