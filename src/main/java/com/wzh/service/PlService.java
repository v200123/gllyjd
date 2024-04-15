package com.wzh.service;

import com.wzh.dao.FoodMapper;
import com.wzh.dao.PlMapper;
import com.wzh.model.Food;
import com.wzh.model.Pl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlService {
    @Resource
    PlMapper plMapper;
    public int save(Pl pl) {
        return plMapper.insertSelective(pl);
    }
    //    删除数据
    public int del(String id) {
        return plMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public Pl get(String id) {
        return plMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( Pl u) {
        return plMapper.updateByPrimaryKeySelective(u);
    }

    public List<Pl> findAll(Pl pl) {
        return plMapper.findAll(pl);
    }
}
