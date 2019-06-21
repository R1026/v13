package com.kdx.v13_product_service;

import com.kdx.api.IProductService;
import com.kdx.entity.TProduct;
import com.kdx.entity.TProductDesc;
import com.kdx.pojo.TProductVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13ProductServiceApplicationTests {

	@Autowired
	private IProductService productService;

	@Autowired
	private DataSource dataSource;

	@Test
	public void dataSourceTest() throws SQLException {
		System.out.println(dataSource.getConnection());
	}


	@Test
	public void contextLoads() {

//		TProductVO tProductVO = new TProductVO();
//		TProduct tProduct = new TProduct();
//		tProduct.setName("华为笔记本");
//		tProduct.setPrice(5999L);
//		tProduct.setSalePrice(5299L);
//		tProduct.setImages("123");
//		tProduct.setSalePoint("良心好货！");
//		tProduct.setTypeId(2L);
//		tProduct.setTypeName("笔记本");
//
//		tProductVO.setProduct(tProduct);
//		tProductVO.setProductDesc("华为笔记本，新一代性能旗舰!");
//		System.out.println(productService.addProduct(tProductVO));

//		System.out.println(productService.deleteByPrimaryKey(1L));

		List<Long> list = new ArrayList<>();

		list.add(8L);
		list.add(9L);
		list.add(10L);
		list.add(7L);
		int result = productService.batchDel(list);
		System.out.println(result);
	}

}
