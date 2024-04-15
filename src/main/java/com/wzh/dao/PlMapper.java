package com.wzh.dao;

import com.wzh.model.Orders;
import com.wzh.model.Pl;

import java.util.List;

public interface PlMapper {
    int deleteByPrimaryKey(String id);

    int insert(Pl record);

    int insertSelective(Pl record);

    Pl selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Pl record);

    int updateByPrimaryKey(Pl record);

    List<Pl> findAll(Pl pl);

}
