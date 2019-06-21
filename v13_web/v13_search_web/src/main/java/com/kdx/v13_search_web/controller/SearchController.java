package com.kdx.v13_search_web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kdx.api.ISearchService;
import com.kdx.common.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kdx
 * @Date 2019/6/17
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("searchByKeyword")
    public String searchByKeyword(String keyword,int start, ModelMap map){

        ResultBean result = searchService.queryByKeyword(keyword,start);
        map.put("result",result);
        return "search";
    }
}
