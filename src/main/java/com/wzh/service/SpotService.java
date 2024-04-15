package com.wzh.service;

import com.wzh.dao.SpotMapper;
import com.wzh.dao.UserMapper;
import com.wzh.model.Spot;
import com.wzh.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpotService {
    @Resource
    SpotMapper spotMapper;
    public int save(Spot spot) {
        return spotMapper.insertSelective(spot);
    }
    //    删除数据
    public int del(String id) {
        return spotMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public Spot get(String id) {
        return spotMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( Spot u) {
        return spotMapper.updateByPrimaryKeySelective(u);
    }

    public List<Spot> findAll(Spot spot) {
        return spotMapper.findAll(spot);
    }


    public List<Spot> findgh(Spot spot) {
        return spotMapper.findgh(spot);
    }


}
