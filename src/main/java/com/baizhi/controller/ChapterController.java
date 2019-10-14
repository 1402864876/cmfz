package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("queryPage")
    @ResponseBody
    public Map<String,Object> queryPage(Integer page,Integer rows ,String id){
       return chapterService.queryByPage(page,rows,id);
    }
    @ResponseBody
    @RequestMapping("edit")
    public String edit(String oper, Chapter chapter,String albumId){
        System.out.println(oper+"---"+chapter);
        return chapterService.edit(oper, chapter, albumId);
    }
    @RequestMapping("upload")
    @ResponseBody
    public void upload(HttpSession session, String id, MultipartFile filePath){
        String mp3 = session.getServletContext().getRealPath("mp3");  //文件存储位置
        File file = new File(mp3);
        if(!file.exists()){ file.mkdirs(); }  //判断此文件夹是否存在
        String originalFilename = filePath.getOriginalFilename(); //获取文件名
        try {
            filePath.transferTo(new File(mp3,originalFilename));  //上传音频文件
            String realPath = session.getServletContext().getRealPath("/mp3/"+originalFilename);//获取文件的位置
            File file1 = new File(realPath);
            //获取文件的大小
            long length = file1.length();
            //获取音频时长  单位为秒
            AudioFile read = AudioFileIO.read(file1);
            AudioHeader audioHeader = read.getAudioHeader();
            int trackLength = audioHeader.getTrackLength();
            //获取分钟数
            Integer m = trackLength/60;
            //获取秒数
            Integer s = trackLength%60;
            String newSize = m+"分"+s+"秒";   //大小
            //将文件大小强转成double类型
            double size = (double) length;
            //获取文件大小  单位是mb
            double ll = size/1024/1024;
            //取double小数点都两位  四舍五入
            BigDecimal bg = new BigDecimal(ll).setScale(2, RoundingMode.UP);
            String time = bg.toString()+"MB";   //时长
            chapterService.updateChapter(id,time,newSize,originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("download")
    @ResponseBody
    public void download(HttpSession session, String filePath, HttpServletResponse response){
        try {
            String realPath = session.getServletContext().getRealPath("/mp3");
            File file = new File(realPath,filePath);
            System.out.println(filePath+"------");
            String encode = URLEncoder.encode(filePath, "UTF-8");
            String replace = encode.replace("+", "%20");
            response.setHeader("content-disposition","attachment;fileName="+replace);
            FileUtils.copyFile(file,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
