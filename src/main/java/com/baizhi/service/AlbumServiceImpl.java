package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String,Object> queryByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        //查询总条数
        Integer count = albumMapper.count(); //总条数
        //展示的数据
        List<Album> albums = new ArrayList<Album>();
        //计算总页数
        Integer pageSize = count%rows==0?count/rows:count/rows+1;
        if(page==1){
            albums = albumMapper.queryByPage(0, rows);
        }else {
            albums = albumMapper.queryByPage(page, rows);
        }
        map.put("records",count); //总条数
        map.put("rows",albums);  //页面中要展示的内容
        map.put("page",page); //当前页
        map.put("total",pageSize);  //总页数
        return map;
    }

    @Override
    public String addAlbum(String oper, Album album,String ids) {
        if(oper.equals("add")){
            String replace = UUID.randomUUID().toString().replace("-", "");
            album.setId(replace);   //添加ID
            album.setPublish_date(new Date()); //添加上传时间
            albumMapper.addAlbum(album);
            return replace;
        }else if(oper.equals("edit")){
            albumMapper.updateAlbum(album);
        }else if(oper.equals("del")){
            String[] split = ids.split(",");
            albumMapper.deleteAlbum(split);
        }
        return null;
    }

    @Override   //修改上传的图片的路径
    public void updateCover(String id, String cover) {
        albumMapper.updatePath(id,cover);
    }

    @Override
    public void updateAlbumCount(String id, Integer count) {
        albumMapper.updateAlbumCount(id,count);
    }
}
