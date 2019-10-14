package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page,Integer rows){
        return articleService.queryAll(page,rows);
    }
    @ResponseBody
    @RequestMapping("add")
    public void add(Article article){
        article.setId(UUID.randomUUID().toString().replace("-",""));
        article.setCreateDate(new Date());
        if(article.getStatus().equals("正在展示")){ article.setStatus("y"); }else{ article.setStatus("n"); }
        articleService.add(article);
    }
    @RequestMapping("update")
    @ResponseBody
    public void update(Article article){
        if(article.getStatus().equals("正在展示")){ article.setStatus("y"); }else{ article.setStatus("n"); }
        articleService.update(article);
    }
}
