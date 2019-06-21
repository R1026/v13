package com.kdx.mapper;

import com.kdx.common.base.IBaseDao;
import com.kdx.entity.TProductType;

import java.util.List;

public interface TProductTypeMapper extends IBaseDao<TProductType>{

    List<TProductType> getType();

    TProductType getParent(Long id);
}