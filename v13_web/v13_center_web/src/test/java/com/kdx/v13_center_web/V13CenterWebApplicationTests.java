package com.kdx.v13_center_web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.kdx.api.IProductService;
import com.kdx.entity.TProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13CenterWebApplicationTests {

	@Autowired
	private FastFileStorageClient client;

	@Test
	public void contextLoads() {

		File file = new File("G:\\Idea\\workspace\\v13\\v13_web\\v13_center_web\\fll.jpg");
		try {
			FileInputStream inputStream = new FileInputStream(file);
			StorePath storePath = client.uploadFile(inputStream, file.length(), "jpg", null);
			System.out.println(storePath.getFullPath());
			System.out.println(storePath.getGroup());
			System.out.println(storePath.getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
