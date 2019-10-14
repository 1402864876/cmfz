package com.baizhi.service;

import com.baizhi.entity.City;
import com.baizhi.entity.Echarts;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Echarts> countAndName() {
        return userMapper.queryAll();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Integer> line() {
        return userMapper.line();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Province> selectAll() {
        return userMapper.selectAll();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<City> selectAllById(String id) {
        return userMapper.selectAllById(id);
    }

    @Override
    public void addUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setStatus("y");
        user.setCreateDate(new Date());
        userMapper.addUser(user);
    }
}
