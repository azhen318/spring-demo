package com.example.springboot.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private int id;

    private String name;

    private Integer age;


    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
