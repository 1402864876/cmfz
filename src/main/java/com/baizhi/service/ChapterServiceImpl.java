package com.baizhi.service;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;
    @Autowired
    AlbumService albumService;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(Integer page, Integer rows,String albumId) {
        HashMap<String, Object> chapterMap = new HashMap<String,Object>();
        chapterMap.put("page",page); //当前页
        //查询展示的数据
        List<Chapter> chapters = new ArrayList<>();
        if(page==1){
            chapters = chapterMapper.queryByPage(0, rows,albumId);
        }else{
            chapters = chapterMapper.queryByPage(page, rows,albumId);
        }
        //查询总条数
        Integer count = chapterMapper.count(albumId);
        //计算总页数
        Integer pageCount = count%rows==0?count/rows:count/rows+1;
        chapterMap.put("records",count); //总条数
        chapterMap.put("rows",chapters);  //页面中要展示的内容
        chapterMap.put("total",pageCount);  //总页数
        return chapterMap;
    }
    @Override  //增删改
    public String edit(String oper, Chapter chapter, String id) {
        if(oper.equals("add")){
            String replace = UUID.randomUUID().toString().replace("-", "");
            chapter.setId(replace);
            chapterMapper.addChapter(chapter);
            Integer count = chapterMapper.count(chapter.getAlbum_id());
            albumService.updateAlbumCount(chapter.getAlbum_id(),count);
            return replace;
        }else if(oper.equals("edit")){
            chapterMapper.updateEdit(chapter);
        }else if("del".equals(oper)){
            String[] split = chapter.getId().split(",");
            chapterMapper.deleteChapterById(split);
            Integer count = chapterMapper.count(chapter.getAlbum_id());
            albumService.updateAlbumCount(chapter.getAlbum_id(),count);
        }
        return null;
    }

    @Override   //修改音频的信息
    public void updateChapter(String id, String size, String time,String filePath) {
        chapterMapper.updateChapter(id,size,time,filePath);
    }
}
