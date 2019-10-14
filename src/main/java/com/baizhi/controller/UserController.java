package com.baizhi.controller;

import com.baizhi.entity.City;
import com.baizhi.entity.Echarts;
import com.baizhi.entity.Province;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("queryMap")
    @ResponseBody
    public List<Echarts> queryMap(){
        List<Echarts> echarts = userService.countAndName();
        return echarts;
    }
    @ResponseBody
    @RequestMapping("line")
    public List<Integer> line(){
        return userService.line();
    }
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Province> selectAll(){
        return userService.selectAll();
    }

    @RequestMapping("selectCity")
    @ResponseBody
    public List<City> selectCity(String id){
        return userService.selectAllById(id);
    }

    @RequestMapping("addUser")
    public ModelAndView addUser(User user){
        userService.addUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/main/main.jsp");
        return modelAndView;
    }
    @RequestMapping("goEasy")
    public void goEasy(String content){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-b0e8ecd689ab44baa461d1946d81c058");
        goEasy.publish("my_channel",content);
    }
}