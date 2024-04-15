package com.wzh.dao;

import com.wzh.model.Spot;

import java.util.List;

public interface SpotMapper {
    int deleteByPrimaryKey(String id);

    int insert(Spot record);

    int insertSelective(Spot record);

    Spot selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Spot record);

    int updateByPrimaryKey(Spot record);

    List<Spot> findAll(Spot spot);


    List<Spot> findgh(Spot spot);

}
