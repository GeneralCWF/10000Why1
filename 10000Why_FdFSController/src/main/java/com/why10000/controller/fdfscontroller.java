package com.why10000.controller;

import com.why10000.common.result.R;
import com.why10000.common.util.FDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class fdfscontroller {
    @Autowired
    private FDFSUtil fdfsUtil;


    private String url="http://39.104.127.63/";
    @PostMapping("/fileup.do")
    public R fileup(@RequestParam("feri")CommonsMultipartFile commonsMultipartFile) throws Exception {
        String filename=commonsMultipartFile.getOriginalFilename();
        String sub=filename.substring(filename.lastIndexOf(".")+1);
        String filepath= fdfsUtil.upload_file(commonsMultipartFile.getBytes(),sub,null);
        System.out.println("FASTDFS文件上传："+filepath);
        return new R(1001,url+filepath);
    }
}
