package com.example.springboot.thymeleaf.controller;

import com.example.springboot.model.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("host", "https://github.com/azhen318/spring-demo");
        Student s1=new Student(1,"A",15);
        Student s2=new Student(2,"B",16);
        Student s3=new Student(3,"C",17);
        List<Student> list=new ArrayList<Student>();
        list.add(s1);list.add(s2);list.add(s3);

        map.addAttribute("stuent",list);
        return "index";
    }





}
