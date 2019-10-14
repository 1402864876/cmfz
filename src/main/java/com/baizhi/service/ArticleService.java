package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    public Map<String,Object> queryAll(Integer page, Integer rows);
    public void add(Article article);
    public void update(Article article);
}