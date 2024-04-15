package com.wzh.service;

import com.wzh.dao.OrdersMapper;
import com.wzh.dao.SpotMapper;
import com.wzh.model.Orders;
import com.wzh.model.Spot;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersService {
    @Resource
    OrdersMapper ordersMapper;
    public int save(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }
    //    删除数据
    public int del(String id) {
        return ordersMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public Orders get(String id) {
        return ordersMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( Orders u) {
        return ordersMapper.updateByPrimaryKeySelective(u);
    }

    public List<Orders> findAll(Orders o) {
        return ordersMapper.findAll(o);
    }
}
