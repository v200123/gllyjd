package com.wzh.service;

import com.wzh.dao.FoodMapper;
import com.wzh.dao.RecomMapper;
import com.wzh.model.Food;
import com.wzh.model.Recom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecomService {
    @Resource
    RecomMapper recomMapper;
    public int save(Recom recom) {
        return recomMapper.insertSelective(recom);
    }
    //    删除数据
    public int del(String id) {
        return recomMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public Recom get(String id) {
        return recomMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( Recom u) {
        return recomMapper.updateByPrimaryKeySelective(u);
    }

    public List<Recom> findAll(Recom recom) {
        return recomMapper.findAll(recom);
    }
}
