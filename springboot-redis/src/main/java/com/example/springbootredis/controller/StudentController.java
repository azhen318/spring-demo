package com.example.springbootredis.controller;

import com.example.springboot.model.vo.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

    /**
     * 缓存机制
     * @param id
     * @return
     */
    @Cacheable(value = "caffeine-cache",key = "#id",unless = "#s !=null")
    @GetMapping("get/{id}")
    public Student getById(@PathVariable("id") int id){
        Student s=new Student(id,"查询"+id);
        return s;
    }

    @CachePut(value = "caffeine-cache",key = "#id",unless = "#s !=null")
    @GetMapping("save/{id}")
    public Student saveById(@PathVariable("id") int id){
        Student s=new Student(id,"保存"+id);
        return s;
    }


    @CacheEvict(value = "caffeine-cache",key ="#id")
    @GetMapping("del/{id}")
    public void delById(@PathVariable("id") int id){
        return;
    }

}
