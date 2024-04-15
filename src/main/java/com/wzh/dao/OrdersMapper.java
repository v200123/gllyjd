package com.wzh.dao;

import com.wzh.model.Orders;
import com.wzh.model.Spot;

import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> findAll(Orders orders);

}
