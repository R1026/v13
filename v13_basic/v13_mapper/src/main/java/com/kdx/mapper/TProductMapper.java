package com.kdx.mapper;

import com.kdx.common.base.IBaseDao;
import com.kdx.entity.TProduct;

import java.util.List;

public interface TProductMapper extends IBaseDao<TProduct>{

    int batchDel(List<Long> list);
}