package com.wzh.dao;

import com.wzh.model.Orders;
import com.wzh.model.Relevance;

import java.util.List;

public interface RelevanceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Relevance record);

    int insertSelective(Relevance record);

    Relevance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Relevance record);

    int updateByPrimaryKey(Relevance record);

    List<Relevance> findAll(Relevance relevance);

}
