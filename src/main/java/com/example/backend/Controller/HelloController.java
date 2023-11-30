package com.example.backend.Controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping()
    public String getHello(@RequestParam String str){
        return str;
    }

//    @PostMapping("/file")
//    public void saveFile(@RequestParam("file") MultipartFile file) throws IOException {
//        //1.1 获取文件名称
//        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
//
////        String basePath = "D:/video";
////        String datePath =
////                new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
////
////        String localDir = basePath + datePath;
////        //2.3 需要创建目录
////        File dirFile = new File(localDir);
////        if(!dirFile.exists()){
////            dirFile.mkdirs();
////        }
//        String finalPath = "https://box.nju.edu.cn/d/07d3ddadbcd84a228e26/" + fileName;
//
//        File realFile = new File(finalPath);
//        file.transferTo(realFile);
//    }
}
