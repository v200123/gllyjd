package com.wzh.service;

import com.wzh.dao.RelevanceMapper;
import com.wzh.model.Orders;
import com.wzh.model.Relevance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RelevanceService {
    @Resource
    RelevanceMapper relevanceMapper;
    public int save(Relevance relevance) {
        return relevanceMapper.insertSelective(relevance);
    }
    //    删除数据
    public int del(String id) {
        return relevanceMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public Relevance get(String id) {
        return relevanceMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( Relevance u) {
        return relevanceMapper.updateByPrimaryKeySelective(u);
    }

    public List<Relevance> findAll(Relevance o) {
        return relevanceMapper.findAll(o);
    }
}
