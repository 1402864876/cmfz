package com.baizhi.mapper;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    public List<Admin> queryAll();
    //管理员登陆
    public Admin queryAdmin(@Param(value = "username") String username, @Param(value = "password") String password);
}
