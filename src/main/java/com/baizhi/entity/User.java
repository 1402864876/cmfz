package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String dharma;  //法名
    private String name;    //姓名
    private String province;//省份
    private String city;    //城市
    private String username;//用户名
    private String password;//密码
    private String status;  //状态
    private Date createDate;//注册时间
}