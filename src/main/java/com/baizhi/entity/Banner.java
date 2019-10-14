package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @Excel(name = "ID" ,width = 35,height = 20)
    private String id;
    @Excel(name = "图片路径", type = 2 ,width = 50 , height = 35)
    private String path; //图片路径
    @Excel(name = "图片名称",height = 20)
    private String title; //图片名称
    @Excel(name = "状态",replace = {"展示_1,不展示_2"})
    private int status;  //状态
    @Excel(name = "描述")
    private String desc;  //描述
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间",format = "yyyy-MM-dd",width = 20)
    private Date createDate; //创建时间
}
