package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    Map<String,Object> queryByPage(Integer page, Integer rows,String albumId);
    String edit(String oper, Chapter chapter,String id);
    void updateChapter(String id,String size,String time,String filePath);
}
