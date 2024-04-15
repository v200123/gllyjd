package com.wzh.service;

import com.wzh.dao.FoodMapper;
import com.wzh.dao.SpotMapper;
import com.wzh.model.Food;
import com.wzh.model.Spot;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodService {
    @Resource
    FoodMapper foodMapper;
    public int save(Food food) {
        return foodMapper.insertSelective(food);
    }
    //    删除数据
    public int del(String id) {
        return foodMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public Food get(String id) {
        return foodMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( Food u) {
        return foodMapper.updateByPrimaryKeySelective(u);
    }

    public List<Food> findAll(Food food) {
        return foodMapper.findAll(food);
    }
}
