package com.wzh.dao;

import com.wzh.model.Pl;
import com.wzh.model.Recom;

import java.util.List;

public interface RecomMapper {
    int deleteByPrimaryKey(String id);

    int insert(Recom record);

    int insertSelective(Recom record);

    Recom selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Recom record);

    int updateByPrimaryKey(Recom record);

    List<Recom> findAll(Recom recom);

}
