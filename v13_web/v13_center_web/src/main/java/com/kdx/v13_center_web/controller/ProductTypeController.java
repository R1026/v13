package com.kdx.v13_center_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kdx.api.IProductTypeService1;
import com.kdx.common.pojo.ResultBean;
import com.kdx.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/13
 */
@Controller
@RequestMapping("productType")
public class ProductTypeController {

    @Reference
    private IProductTypeService1 iProductTypeService;

    @RequestMapping("getType")
    @ResponseBody
    public ResultBean getType(){

        List<TProductType> list = iProductTypeService.getType();
        ResultBean bean = new ResultBean();
        bean.setStatusCode("200");
        bean.setData(list);

        return bean;
    }

    @RequestMapping("getParent/{id}")
    @ResponseBody
    public ResultBean getParent(@PathVariable Long id){

        TProductType tProductType = iProductTypeService.getParent(id);
        ResultBean bean = new ResultBean();
        bean.setStatusCode("200");
        bean.setData(tProductType);

        return bean;
    }

}
