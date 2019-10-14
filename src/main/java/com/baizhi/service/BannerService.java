package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.HashMap;
import java.util.List;

public interface BannerService {
    public HashMap<String,Object> queryPage(Integer page, Integer rows);  //page当前页  rows每页显示的条数
    String edit(String id,String oper,String title,String status,String desc,String path);
    void updatePath(String id,String path);
    List<Banner> esayPoi(String[] ids);
    List<Banner> queryAll();
}
