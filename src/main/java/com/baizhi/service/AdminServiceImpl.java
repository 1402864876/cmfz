package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Admin> queryAll() {
        return adminMapper.queryAll();
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryAdmin(String username, String password, String code, HttpSession session) {
        Admin admin = adminMapper.queryAdmin(username, password); //通过数据库查找该管理员
        String oldCode = (String) session.getAttribute("validationCode");  //后台生成的验证码
        if(oldCode.equals(code)){  //判断验证码是否正确
            if(admin != null){  //判断该用户是否存在
                return null;
            }else{
                return "usernameError";
            }
        }else{
            return "codeError";
        }
    }
}