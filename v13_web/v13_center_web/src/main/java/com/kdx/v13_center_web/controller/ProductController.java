package com.kdx.v13_center_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.kdx.api.IProductService;
import com.kdx.api.ISearchService;
import com.kdx.common.constant.RabbitMQConstant;
import com.kdx.common.pojo.ResultBean;
import com.kdx.common.util.HttpClientUtil;
import com.kdx.entity.TProduct;
import com.kdx.pojo.TProductVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/11
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;

    @Reference
    private ISearchService searchService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("get/{id}")
    @ResponseBody
    public TProduct getById(@PathVariable Long id) {
        TProduct tProduct = productService.selectByPrimaryKey(id);
        return tProduct;
    }

    @RequestMapping("list")
    public String list(ModelMap map) {

        List<TProduct> list = productService.getList();
        map.put("list", list);
        return "product/list";
    }

    @RequestMapping("page/{currentPage}")
    public String getPage(@PathVariable int currentPage,ModelMap map) {

        PageInfo<TProduct> page = productService.getPage(currentPage);
        map.put("page",page);
        return "product/list";
    }

    @PostMapping("addProduct")
    public String addProduct(TProductVO tProductVO) throws IOException {

        int result = productService.addProduct(tProductVO);
        //数据增量同步
        //根据id获得商品信息，往搜索服务器添加新增商品信息
        //searchService.updateById(result);

        //通过HttpClient伪造浏览器访问生成静态页的地址，添加商品静态页
        //String str = HttpClientUtil.doGet("http://localhost:9093/item/creatHTMLById/"+result);

        //更改为rabbitmq异步操作
        rabbitTemplate.convertAndSend(RabbitMQConstant.PRODUCT_CENTER_EXCHANGE,"product.add",result);
        //更改为rabbitmq异步操作
        String str = "http://localhost:9093/item/creatHTMLById/"+result;
        rabbitTemplate.convertAndSend(RabbitMQConstant.PRODUCT_CENTER_EXCHANGE,"add_item",str);
        return "redirect:/product/page/1";
    }

    @RequestMapping("del/{id}")
    @ResponseBody
    public ResultBean delProduct(@PathVariable Long id){

        int result = productService.deleteByPrimaryKey(id);
        if(result>0){

            return new ResultBean("200","删除成功!");

        }
        return new ResultBean("404","找不到数据...");
    }


    //前端页面传递过来一个数组，转换成一个集合
    @RequestMapping("batchDel")
    @ResponseBody
    public ResultBean batchDel(@RequestParam(name = "ids") List list){
        int result = productService.batchDel(list);
        if(result>0){
            return new ResultBean("200","批量删除成功.");
        }

        return new ResultBean("404","批量删除失败。。。.");
    }


    //点击修改商品信息返回数据
    @PostMapping("getMsg/{id}")
    @ResponseBody
    public ResultBean getMsg(@PathVariable Long id){

        System.out.println(id);
        TProduct product = productService.selectByPrimaryKey(id);

        return new ResultBean("200",product);
    }
}
