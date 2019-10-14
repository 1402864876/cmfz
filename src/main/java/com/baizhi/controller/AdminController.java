package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("selectAdminAll")
    @ResponseBody   //查询所有的管理员
    public List<Admin> selectAdminAll(){
        return adminService.queryAll();
    }
    @RequestMapping("login")
    @ResponseBody   //管理员登陆
    public Map<String,String> loginAdmin(String username, String password, String code, HttpSession session){
        Map<String, String> map = new HashMap<>();
        String newMessage = null;
        String message = adminService.queryAdmin(username, password, code, session);
        if("codeError".equals(message)){
            newMessage = "codeError";
        }else if("usernameError".equals(message)){
            newMessage = "usernameError";
        }else {
            newMessage = "success";
            session.setAttribute("username",username);
        }
        map.put("message",newMessage);
        return map;
    }
    @RequestMapping("out")
    public String LoggingOut(HttpSession session){
        session.invalidate();
        return "redirect:/login/login.jsp";
    }
}