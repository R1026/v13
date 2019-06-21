package com.kdx.v13_item_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kdx
 * @Date 2019/6/20
 */
@Configuration
public class CommonConfig {

    @Bean
    public ThreadPoolExecutor initThreadPoolExecutor(){

        //查看当前硬件有多少核
        int cpus = Runtime.getRuntime().availableProcessors();
        //
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                cpus,cpus*2,10, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        return pool;
    }
}
