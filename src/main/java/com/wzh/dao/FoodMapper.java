package com.wzh.dao;

import com.wzh.model.Food;
import com.wzh.model.Spot;

import java.util.List;

public interface FoodMapper {
    int deleteByPrimaryKey(String id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> findAll(Food food);

}
