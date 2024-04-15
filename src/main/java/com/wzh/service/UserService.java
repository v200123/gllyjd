package com.wzh.service;

import com.wzh.dao.UserMapper;
import com.wzh.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    public int save(User user) {
        return userMapper.insertSelective(user);
    }
    //    删除数据
    public int del(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }
    //    获取数据
    public User get(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
    //    更新数据
    public int  update( User u) {
        return userMapper.updateByPrimaryKeySelective(u);
    }

    public User getByUsername(String username){
        return userMapper.getByUsername(username);
    }
    public List<User> findAll(User user) {
        return userMapper.findAll(user);
    }
}
