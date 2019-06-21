package com.kdx.v13_item_web;

import com.kdx.v13_item_web.controller.ItemController;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13ItemWebApplicationTests {

	@Autowired
	private Configuration configuration;

	@Autowired
	private ItemController itemController;

	@Test
	public void contextLoads() throws IOException, TemplateException {

		Template template = configuration.getTemplate("hello.ftl");
		Map<String,Object> data = new HashMap<>();
		data.put("username","kedongxing");

		FileWriter writer = new FileWriter("G:\\Idea\\workspace\\v13\\v13_web\\v13_item_web\\src\\main\\resources\\static\\hello.html");
		template.process(data,writer);
		System.out.println("生成静态页成功!");

	}

	@Test
	public void batchCreatHTMLTest(){



	}

}
