package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    Map<String,Object> queryByPage(Integer page, Integer rows);
    String addAlbum(String oper, Album album,String ids);
    void updateCover(String id,String cover);
    void updateAlbumCount(String id,Integer count);
}
