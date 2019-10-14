package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private String id;
    private String filePath;  //音频路径
    private String title; //名字
    private String size;//音频大小
    private String time; //音频时长
    private Integer status; //状态
    private String album_id; //所属专辑的id
}