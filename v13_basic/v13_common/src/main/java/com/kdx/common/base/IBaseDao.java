package com.kdx.common.base;

import java.util.List;

/**
 * @author kdx
 * @Date 2019/6/11
 */
public interface IBaseDao<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);

    List<T> getList();
}
