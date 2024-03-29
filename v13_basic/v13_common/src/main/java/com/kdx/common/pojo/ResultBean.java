package com.kdx.common.pojo;

import java.io.Serializable;

/**
 * @author kdx
 * @Date 2019/6/12
 */
public class ResultBean<T> implements Serializable{

    private String statusCode;
    private T data;

    public ResultBean() {

    }

    public ResultBean(String statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
