package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@RestController
@RequestMapping("kindeditor")
public class Kindeditor {
    @RequestMapping("upload")
    public Map<String,Object> upload(HttpServletRequest request, MultipartFile img, HttpSession session) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String originalFilename = new Date().getTime()+"_"+img.getOriginalFilename();//文件名
        String realPath = session.getServletContext().getRealPath("/img");//相对路径
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();  //不存在进行创建
        }
        img.transferTo(new File(realPath,originalFilename));
        //获取当前网站的协议名
        String scheme = request.getScheme();
        //获取本机的ip地址
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        //获取端口号
        int port = request.getServerPort();
        //获取项目名
        String contextPath = request.getContextPath();
        String url = scheme+"://"+hostAddress+":"+port+contextPath+"/img/"+originalFilename;
        map.put("error",0);
        map.put("url",url);
        return map;
    }
    @RequestMapping("allImages")
    public Map<String,Object> all(HttpServletRequest request,HttpSession session) throws UnknownHostException {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        String[] allimg = file.list();
        for (String s : allimg) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("is_dir",false);
            map1.put("has_file",false);
            //获取文件的大小
            File file1 = new File(realPath, s);
            long length = file1.length();
            map1.put("filesize",length);
            map1.put("dir_path","");
            map1.put("is_photo",true);
            //获取文件的后缀名
            String s1 = s.substring(s.lastIndexOf(".") + 1);
            map1.put("filetype",s1);
            map1.put("filename",s);
            map1.put("datetime",new Date());
            list.add(map1);
        }
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");
        InetAddress localHost = InetAddress.getLocalHost();
        //获取当前本机ip地址
        String hostAddress = localHost.getHostAddress();
        //获取当前网站的协议名  http
        String scheme = request.getScheme();
        //获取当前端口号
        int port = request.getServerPort();
        //获取项目名
        String path = request.getContextPath();
        String url=scheme+"://"+hostAddress+":"+port+path+"/img/";
        map.put("current_url",url);
        int size = list.size();
        map.put("total_count",size);
        map.put("file_list",list);
        return map;
    }
    public void m12(){
        System.out.println("this is first update");
        System.out.println("this it't myEdit");
        System.out.println("wo ai wo jia");
    }
}
