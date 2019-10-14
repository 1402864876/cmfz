package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    Integer count(); //查询总条数
    List<Album> queryByPage(@Param("start")Integer start, @Param("rows")Integer rows);
    void addAlbum(Album album);
    void updatePath(@Param("id") String id,@Param("cover") String cover);
    void updateAlbum(Album album);
    void deleteAlbum(String[] id);
    void updateAlbumCount(@Param("id")String id,@Param("count") Integer count);
}
