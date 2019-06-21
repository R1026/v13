package com.kdx.v13_product_service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kdx.mapper")
@EnableDubbo
public class V13ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V13ProductServiceApplication.class, args);
	}

}
