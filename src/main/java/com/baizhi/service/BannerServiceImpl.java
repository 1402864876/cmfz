package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public HashMap<String, Object> queryPage(Integer page, Integer rows) {
        //查询总条数
        Integer count = bannerMapper.count(); //查到的总条数
        //计算总页数
        Integer pageCount = count%rows==0?count/rows:count/rows+1;  //总页数
        List<Banner> banners = new ArrayList<>();
        if(page==1){
            banners = bannerMapper.queryPage(0, rows); //查询到的图片集合
        }else {
            banners = bannerMapper.queryPage(page, rows); //查询到的图片集合
        }
        HashMap<String, Object> map = new HashMap<String,Object>();
        map.put("rows",banners);  //页面中要展示的内容
        map.put("records",count); //总条数
        map.put("page",page); //当前页
        map.put("total",pageCount);  //总页数
        return map;
    }
    @Override  //增删改  根据oper来判断
    public String edit(String id,String oper, String title, String status, String desc, String path) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setTitle(title);
        if(status!=null){
            Integer integer = Integer.valueOf(status);
            banner.setStatus(integer);
        }
        banner.setDesc(desc);
        if(oper.equals("add")){  //添加数据
            String replace = UUID.randomUUID().toString().replace("-", "");
            banner.setId(replace);
            banner.setCreateDate(new Date());
            banner.setPath(path);
            bannerMapper.addBanner(banner);
            return replace;
        }else if(oper.equals("edit")){
            System.out.println(banner+"-------");
            bannerMapper.update(banner);
        }else if(oper.equals("del")){
            String[] split = id.split(",");
            bannerMapper.deleteBanner(split);
        }
        return null;
    }

    @Override  //修改图片的路径
    public void updatePath(String id, String path) {
        bannerMapper.updatePath(id,path);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Banner> esayPoi(String[] ids) {
        return bannerMapper.esayPoi(ids);
    }

    @Override
    public List<Banner> queryAll() {
        return bannerMapper.queryAll();
    }
}
