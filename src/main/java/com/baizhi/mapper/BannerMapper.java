package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    List<Banner> queryPage(@Param("start") Integer start, @Param("rows") Integer rows); //分页查询图片
    void addBanner(Banner banner);  //添加数据
    Integer count();  // 查询总条数
    void updatePath(@Param("id") String id,@Param("path") String path);//修改路径
    void update(Banner banner);  //修改数据
    void deleteBanner(String[] ids);
    List<Banner> esayPoi(String[] ids);
    List<Banner> queryAll();
}
