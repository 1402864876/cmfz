package com.baizhi.mapper;

import com.baizhi.entity.City;
import com.baizhi.entity.Echarts;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;

import java.util.List;

public interface UserMapper {
    List<Echarts> queryAll();
    List<Integer> line();
    List<Province> selectAll();
    List<City> selectAllById(String id);
    void addUser(User user);
}
