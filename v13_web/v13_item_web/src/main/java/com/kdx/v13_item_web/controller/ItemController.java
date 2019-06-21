package com.kdx.v13_item_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kdx.api.IProductService;
import com.kdx.common.pojo.ResultBean;
import com.kdx.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author kdx
 * @Date 2019/6/18
 */

@Controller
@RequestMapping("item")
public class ItemController {

    @Reference
    private IProductService productService;

    @Autowired
    private Configuration configuration;

    @Autowired
    private ThreadPoolExecutor pool;

    @RequestMapping("creatHTMLById/{id}")
    @ResponseBody
    public ResultBean creatHTMLById(@PathVariable("id") Long id){

        return createHTMLById(id);
    }

    /**
     *
     * 页面改版，可以根据id的集合生存静态页
     * 前端页面传递一个数组，到这里转换为集合
     * @param ids
     * @return
     */
    @RequestMapping("batchCreatHTML")
    @ResponseBody
    public ResultBean bathcCreatHTML(List<Long> ids) throws ExecutionException, InterruptedException {
        //存放批量生成的结果集合
        List<Future<Long>> results = new ArrayList<>(ids.size());

        for (Long id : ids) {
            results.add(pool.submit(new creatHTMLTask(id)));
        }

        //错误集合
        List<Long> errors = new ArrayList<>();
        //查看每个线程执行的结果
        for (Future<Long> future : results) {
            Long id = future.get();
            if (id!=0){
                //生成静态页面出现错误，将返回的错误id记录在集合中
                errors.add(id);
            }
        }
        //如果失败了，应该怎么办？
        //1.是谁失败了？
        //2.失败了，补救方案是什么
        //记录到失败日志
        //create_time id  --> 菜单 （1,5,8）
        //解决方案：
        //方案一：手工模式 直接调用接口去实现
        //方案二：自动模式，定时任务（重试次数，不要超过三次）
        if(errors.size()>0){
            return new ResultBean("500","批量生成页面失败。");
        }
        return new ResultBean("200","批量生成页面成功!");
    }


    /**
     * 根据id获取商品信息生成静态页
     * * @param id
     * @return
     */
    private ResultBean createHTMLById(@PathVariable("id") Long id) {
        try {
            Template template = configuration.getTemplate("item.ftl");
            Map<String,Object> data = new HashMap<>();
            TProduct product = productService.selectByPrimaryKey(id);
            data.put("product",product);
            String serverPath = ResourceUtils.getURL("classpath:static").getPath();
            String filePath = new StringBuilder(serverPath).append(File.separator).append(id).append(".html").toString();
            FileWriter writer = new FileWriter(filePath);
            template.process(data,writer);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean("404","生成模板失败.");
        } catch (TemplateException e) {
            e.printStackTrace();
            return new ResultBean("404","生成静态页失败。");
        }
        return new ResultBean("200","success！！！");
    }

    //内部类
    private class creatHTMLTask implements Callable<Long>{

        private Long id;

        public creatHTMLTask(Long id) {
            this.id = id;
        }

        @Override
        public Long call() throws Exception {

            try {
                Template template = configuration.getTemplate("item.ftl");
                Map<String,Object> data = new HashMap<>();
                TProduct product = productService.selectByPrimaryKey(id);
                data.put("product",product);
                String serverPath = ResourceUtils.getURL("classpath:static").getPath();
                String filePath = new StringBuilder(serverPath).append(File.separator).append(id).append(".html").toString();
                FileWriter writer = new FileWriter(filePath);
                template.process(data,writer);
            } catch (Exception e) {
                e.printStackTrace();
                return id;
            }
            return 0L;
        }
    }
}
