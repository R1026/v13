package com.kdx.v13_search_service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.kdx.mapper")
@EnableDubbo
public class V13SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V13SearchServiceApplication.class, args);
	}

}
