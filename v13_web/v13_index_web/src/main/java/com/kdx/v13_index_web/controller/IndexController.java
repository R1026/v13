package com.kdx.v13_index_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kdx.api.IProductTypeService1;
import com.kdx.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/15
 */


@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService1 iProductTypeService1;

    @RequestMapping("home")
    public String showHome(ModelMap map){

        List<TProductType> list = iProductTypeService1.getList();
        map.put("list",list);
        return "home";
    }

}
