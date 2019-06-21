package com.kdx.v13_product_service.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kdx.api.IProductService;
import com.kdx.common.base.BaseServiceImpl;
import com.kdx.common.base.IBaseDao;
import com.kdx.entity.TProduct;
import com.kdx.entity.TProductDesc;
import com.kdx.mapper.TProductDescMapper;
import com.kdx.mapper.TProductMapper;
import com.kdx.pojo.TProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author kdx
 * @Date 2019/6/11
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductService{

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductDescMapper productDescMapper;

    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }




    @Override
    public PageInfo<TProduct> getPage(int currentPage) {

        PageHelper.startPage(currentPage,2);
        List<TProduct> list = getList();
        PageInfo<TProduct> pageInfo = new PageInfo<TProduct>(list,2);

        return pageInfo;
    }

    @Override
    @Transactional
    public int addProduct(TProductVO tProductVO) {
        TProduct product = tProductVO.getProduct();
        product.setFlag(true);
        System.out.println("-------pre---------------");
        productMapper.insert(product);
        System.out.println("-------post---------------");
        String productDesc = tProductVO.getProductDesc();
        TProductDesc tProductDesc = new TProductDesc();
        tProductDesc.setProductDesc(productDesc);
        tProductDesc.setProductId(product.getId());
        productDescMapper.insert(tProductDesc);

        return product.getId().intValue();
    }

    //由于是逻辑删除，不是物理删除，实际调用的是updata方法
    @Override
    public int deleteByPrimaryKey(Long id) {

        TProduct product = new TProduct();
        product.setId(id);
        product.setFlag(false);
        int result = productMapper.updateByPrimaryKeySelective(product);
        return result;
    }

    @Override
    public int batchDel(List<Long> list) {

        return productMapper.batchDel(list);

    }
}
