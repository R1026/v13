package com.kdx.v13_search_service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.kdx.api.ISearchService;
import com.kdx.common.pojo.ResultBean;
import com.kdx.entity.TProduct;
import com.kdx.mapper.TProductMapper;
import com.kdx.pojo.PageResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kdx
 * @Date 2019/6/17
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    /**
     * 全量同步数据，适用于索引库数据初始化
     *
     * @return
     */
    @Override
    public ResultBean synAllData() {
        List<TProduct> list = productMapper.getList();
        for (TProduct product : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", product.getId());
            document.setField("product_name", product.getName());
            document.setField("product_price", product.getPrice());
            document.setField("product_sale_price", product.getSalePrice());
            document.setField("product_sale_point", product.getSalePoint());
            document.setField("product_images", product.getImages());

            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResultBean("404", "索引库添加信息失败!");
            }
        }

        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("404", "同步索引库数据失败!");
        }
        return new ResultBean("404", "同步数据成功！！！");
    }

    /**
     * 根据关键字查询solr服务器
     * @param keyword
     * @param start
     * @return
     */
    @Override
    public ResultBean queryByKeyword(String keyword, int start) {

        //返回的分页对象
        //查询索引库符合查询条件的总条数，计算总页数
        //传递当前页，导航页码数
        //拿到符合条件的总条数
        int count = this.selectCount(keyword, 32);
        //总页数
        int pages = count % 12 == 0 ? count / 12 : (count / 12) + 1;
        PageResultBean pageResultBean = new PageResultBean(start, 12, (long) count, pages);

        SolrQuery query = new SolrQuery();
        if (StringUtils.isAnyEmpty(keyword)) {
            query.setQuery("product_name:*");
//            query.setQuery("*:*");
        } else {
            query.setQuery("product_keywords:" + keyword);
        }

        query.setStart((start - 1) * 12);
        query.setRows(12);

        //设置高亮
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documents = response.getResults();
            //获取高亮信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<TProduct> list = new ArrayList<>(documents.size());
            for (SolrDocument document : documents) {
                TProduct product = new TProduct();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setName(document.getFieldValue("product_name").toString());
                product.setPrice(Long.parseLong(document.getFieldValue("product_price").toString()));
                product.setSalePrice(Long.parseLong(document.getFieldValue("product_sale_price").toString()));
                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                product.setImages(document.getFieldValue("product_images").toString());

                //针对高亮做设置
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id"));
                List<String> productNameHighlighting = map.get("product_name");
                if (productNameHighlighting != null && productNameHighlighting.size() > 0) {
                    product.setName(productNameHighlighting.get(0));
                } else {
                    product.setName(document.getFieldValue("product_name").toString());
                }

                list.add(product);
            }
            pageResultBean.setList(list);
            pageResultBean.setNavigatePages(3);
            pageResultBean.setUrl("http://localhost:9092/search/searchByKeyword?keyword=" + keyword + "&start=");

        return new ResultBean("200", pageResultBean);
    } catch(SolrServerException |
    IOException e)

    {
        e.printStackTrace();
        return new ResultBean("404", null);
    }

}

    /**
     * 添加商品时根据id往solr服务器存放信息
     * @param id
     * @return
     */
    @Override
    public ResultBean updateById(int id) {
        SolrInputDocument document = new SolrInputDocument();
        TProduct product = productMapper.selectByPrimaryKey((long) id);
        document.setField("id", id);
        document.setField("product_name", product.getName());
        document.setField("product_price", product.getPrice());
        document.setField("product_sale_price", product.getSalePrice());
        document.setField("product_sale_point", product.getSalePoint());
        document.setField("product_images", product.getImages());

        try {
            solrClient.add(document);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("404", "索引库添加信息失败");
        }

        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResultBean("404", "同步索引库失败。");
        }
        return new ResultBean("200", "同步索引库成功！");
    }


    /**
     * 根据关键字查询索引库总条数
     *
     * @param keyword
     * @param numFound
     * @return
     */
    @Override
    public int selectCount(String keyword, int numFound) {

        int count = 0;
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAnyEmpty(keyword)) {
            query.setQuery("product_keywords:" + keyword);
        } else {
            query.setQuery("*:*");
        }
        query.setStart(0);
        query.setRows(numFound);
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documents = response.getResults();
            count = documents.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
