package com.baizhi.service;

import com.baizhi.entity.City;
import com.baizhi.entity.Echarts;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    List<Echarts> countAndName();
    List<Integer> line();
    List<Province> selectAll();   //查询所有的省
    List<City> selectAllById(String id);  //查询所有的市
    void addUser(User user);
}
