package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @RequestMapping("queryPage")
    @ResponseBody    //分页查询
    public Map<String,Object> queryPage(Integer page,Integer rows){
        return albumService.queryByPage(page,rows);
    }
    @RequestMapping("edit")
    @ResponseBody
    public String edit(String oper, Album album,String id){
        return albumService.addAlbum(oper, album,id);
    }
    @RequestMapping("updateCover")
    @ResponseBody  //文件上传并且修改数据库的文件名
    public void updateCover(HttpSession session, String id, MultipartFile cover){
        String originalFilename = cover.getOriginalFilename();//获取文件名
        String newName = new Date().getTime()+originalFilename; //拼接新的文件名
        String realPath = session.getServletContext().getRealPath("/img");//获取存储的路径
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        try {
            cover.transferTo(new File(realPath,newName));  //文件的上传
            albumService.updateCover(id,newName);  //修改路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
