package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String id;
    private String title; //标题
    private String cover;  //专辑封面 图片
    private Double score; //得分
    private String author; //作者
    private String beam; //播音
    private Integer count; //总集数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publish_date;  //上传时间
    private String content;  //专辑简介
}
