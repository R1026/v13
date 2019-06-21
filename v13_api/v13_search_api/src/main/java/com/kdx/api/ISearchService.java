package com.kdx.api;

import com.kdx.common.pojo.ResultBean;

/**
 * @author kdx
 * @Date 2019/6/17
 */
public interface ISearchService {


    public ResultBean synAllData();

    public ResultBean queryByKeyword(String keyword,int start);

    public ResultBean updateById(int id);

    public int selectCount(String keyword,int numFound);
}
