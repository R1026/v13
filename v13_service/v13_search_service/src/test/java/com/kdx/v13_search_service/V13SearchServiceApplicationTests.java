package com.kdx.v13_search_service;

import com.kdx.api.ISearchService;
import com.kdx.common.pojo.ResultBean;
import com.kdx.entity.TProduct;
import com.kdx.pojo.PageResultBean;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13SearchServiceApplicationTests {

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private ISearchService searchService;


	@Test
	public void updataByIdTest(){
		ResultBean resultBean = searchService.updateById(1);
		System.out.println(resultBean.getStatusCode());
		System.out.println(resultBean.getData());
	}


	@Test
	public void queryByKeywordTest(){

		ResultBean result = searchService.queryByKeyword("",1);

		PageResultBean pageResultBean = (PageResultBean) result.getData();
		List<TProduct> list = pageResultBean.getList();
//		List<TProduct> list = (List<TProduct>) result.getData();
		for (TProduct product : list) {
			System.out.println(product.getId()+"------->"+product.getName());
		}
		int[] navigatepageNums = pageResultBean.getNavigatepageNums();
		for (int navigatepageNum : navigatepageNums) {
			System.out.println(navigatepageNum);
		}

	}


	@Test
	public void synDataTest(){
		searchService.synAllData();
	}

	@Test
	public void addTest() throws IOException, SolrServerException {
		//以document为基本单位
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id",1);
		document.setField("product_name","联想拯救者");
		document.setField("product_price",7499);
		document.setField("product_sale_point","良心不痛系列");
		document.setField("product_images","123");

		solrClient.add(document);
		solrClient.commit();
		System.out.println("信息添加成功!");
	}

	@Test
	public void queryTest() throws IOException, SolrServerException {
		SolrQuery queryCondition = new SolrQuery();
		//分词，匹配
		queryCondition.setQuery("product_name:联想小新");
		QueryResponse response = solrClient.query(queryCondition);
		SolrDocumentList results = response.getResults();
		for (SolrDocument result : results) {
			System.out.println(result.get("id")+"..."+result.get("product_name"));
		}
	}

	@Test
	public void delTest() throws IOException, SolrServerException {
		//根据条件删除索引库信息
		solrClient.deleteByQuery("*:*");
		solrClient.commit();
	}

	@Test
	public void delByIdTest() throws IOException, SolrServerException {
		//根据id删除索引库信息
		solrClient.deleteById("1");
		solrClient.commit();
	}

	@Test
	public void contextLoads() {
		System.out.println("ok!!!!!!");
	}

}
