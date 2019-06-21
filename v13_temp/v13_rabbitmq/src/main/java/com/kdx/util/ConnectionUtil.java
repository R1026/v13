package com.kdx.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author kdx
 * @Date 2019/6/20
 */
public class ConnectionUtil {

    private static ConnectionFactory factory;

    static {
        factory = new ConnectionFactory();
        factory.setHost("101.132.71.169");
        factory.setPort(5672);
        factory.setVirtualHost("/kdx");
        factory.setUsername("kdx");
        factory.setPassword("123123");
    }


    public static Connection getCoonnection(){

        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
