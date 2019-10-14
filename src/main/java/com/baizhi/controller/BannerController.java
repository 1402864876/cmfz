package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @ResponseBody
    @RequestMapping("queryByPage") //分页查询所有的图片
    public HashMap<String,Object> selectPage(Integer page,Integer rows){
        return bannerService.queryPage(page, rows);
    }
    @RequestMapping("edit")
    @ResponseBody  //增删改
    public String edit(String id,String oper,String title,String status,String desc,String path){
        String edit = bannerService.edit(id,oper, title, status, desc, path);
        return edit;
    }
    @RequestMapping("upload")
    @ResponseBody  //单独修改图片的路径
    public void upload(MultipartFile path, HttpSession session,String banner){
        String realPath = session.getServletContext().getRealPath("/img"); //获取存放文件的文件夹
        File file = new File(realPath);
        if(!file.exists()){  //判断此文件夹是否存在
            file.mkdirs();
        }
        String filename = path.getOriginalFilename();//获取上传的文件名
        String newFileName = new Date().getTime()+"_"+filename; //添加时间戳
        //文件的上传工作
        try {
            path.transferTo(new File(realPath,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bannerService.updatePath(banner,newFileName);//修改图片的路径
    }
    @ResponseBody
    @RequestMapping("easyPoi")
    public void easyPoi(HttpServletResponse response) throws IOException {
        List<Banner> banners2 = bannerService.queryAll();
        for (Banner banner : banners2) {
            banner.setPath("E:\\resources\\cmfz\\src\\main\\webapp\\img\\"+banner.getPath());
        }
        String encode = URLEncoder.encode("轮播图.xls", "UTF-8");
        response.setHeader("content-disposition","attachment;filename="+encode );
        ServletOutputStream outputStream = response.getOutputStream();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持法明州轮播图","表一"), Banner.class, banners2);
        workbook.write(outputStream);
    }
}