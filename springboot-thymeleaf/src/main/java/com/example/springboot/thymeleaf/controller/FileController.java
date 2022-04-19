package com.example.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Controller
@RequestMapping("/file")
public class FileController {

    private String path="/data/example/";

    @GetMapping("/single")
    public String  single(ModelMap map){
        return "singleFileUpload";
    }

    /**
     * 单文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = path + fileName;

        File dir=new File(path);

        if(!dir.exists())
            dir.mkdirs();

        File dest = new File(filePath);

        Files.copy(file.getInputStream(), dest.toPath());
        return "Upload file success : " + dest.getAbsolutePath();
    }


    @GetMapping("/multi")
    public String  multi(ModelMap map){
        return "multiFileUpload";
    }

    @PostMapping("/multiUpload")
    @ResponseBody
    public String multiUpload(@RequestPart MultipartFile[] files) throws IOException {

        File dir=new File(path);

        if(!dir.exists())dir.mkdirs();

        StringBuilder sb=new StringBuilder();

        Arrays.stream(files).forEach(file->{
            try {
                String fileName = file.getOriginalFilename();
                String filePath = path + fileName;

                File dest = new File(filePath);

                Files.copy(file.getInputStream(), dest.toPath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        return "Upload file success ! ";
    }





}
