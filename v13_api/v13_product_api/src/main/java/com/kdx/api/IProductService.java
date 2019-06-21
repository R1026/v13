package com.kdx.api;

import com.github.pagehelper.PageInfo;
import com.kdx.common.base.IBaseService;
import com.kdx.entity.TProduct;
import com.kdx.pojo.TProductVO;

import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/11
 */
public interface IProductService extends IBaseService<TProduct>{


    PageInfo<TProduct> getPage(int currentPage);

    int addProduct(TProductVO tProductVO);

    int batchDel(List<Long> list);


}
