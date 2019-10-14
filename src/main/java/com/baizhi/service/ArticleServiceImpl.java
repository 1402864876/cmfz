package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //查询总条数
        Integer count = articleMapper.count();
        //计算总页数
        Integer pageCount = count % rows == 0 ?count / rows : count / rows + 1;
        //起始的条数
        Integer start = (page - 1)* rows;
        //查询到的结果
        List<Article> articles = articleMapper.queryPage(start, rows);
        map.put("rows",articles);  //页面中要展示的内容
        map.put("records",count); //总条数
        map.put("page",page); //当前页
        map.put("total",pageCount);  //总页数
        return map;
    }

    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }
}
