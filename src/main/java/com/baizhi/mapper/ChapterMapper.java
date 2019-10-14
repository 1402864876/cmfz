package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    Integer count(String id);
    List<Chapter> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("albumId") String albumId);
    void addChapter(Chapter chapter);
    void updateChapter(@Param("id") String id,@Param("size") String size,@Param("time") String time,@Param("filePath")String filePath);
    void updateEdit(Chapter chapter);
    void deleteChapterById(String[] ids);
}