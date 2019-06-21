package com.kdx.v13_product_service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.kdx.api.IProductTypeService1;
import com.kdx.common.base.BaseServiceImpl;
import com.kdx.common.base.IBaseDao;
import com.kdx.entity.TProductType;
import com.kdx.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/15
 */
@Service
public class ProductTypeServiceImpl1 extends BaseServiceImpl<TProductType> implements IProductTypeService1{


    @Autowired
    private TProductTypeMapper tProductTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return tProductTypeMapper;
    }

    @Override
    public List<TProductType> getType() {
        List<TProductType> list = tProductTypeMapper.getType();
        return list;
    }

    @Override
    public TProductType getParent(Long id) {
        return tProductTypeMapper.getParent(id);

    }


}
