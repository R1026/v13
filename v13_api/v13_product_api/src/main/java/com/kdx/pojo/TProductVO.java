package com.kdx.pojo;

import com.kdx.entity.TProduct;

import java.io.Serializable;

/**
 * @author kdx
 * @Date 2019/6/12
 */
public class TProductVO implements Serializable{

    private TProduct product;
    private String productDesc;

    public TProductVO() {

    }

    public TProductVO(TProduct product, String productDesc) {
        this.product = product;
        this.productDesc = productDesc;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
