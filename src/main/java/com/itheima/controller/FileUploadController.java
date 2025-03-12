package com.itheima.controller;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//上传图片
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        //保证文件名字唯一防止文件覆盖,借助uuid
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("D:\\study\\idea\\ideacodes\\big-event\\files\\"+filename));
        return Result.success("url访问地址...");
    }
}
