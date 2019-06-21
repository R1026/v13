package com.kdx.api;

import com.kdx.common.base.IBaseService;
import com.kdx.entity.TProductType;

import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/15
 */
public interface IProductTypeService1 extends IBaseService<TProductType>{

    List<TProductType> getType();

    TProductType getParent(Long id);
}
