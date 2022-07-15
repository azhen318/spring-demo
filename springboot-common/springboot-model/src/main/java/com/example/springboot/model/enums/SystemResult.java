package com.example.springboot.model.enums;

import com.example.springboot.model.inter.StatusCode;
import lombok.Getter;

@Getter
public enum SystemResult implements StatusCode {


    ERROR(1000,"系统异常"),
    AGE_ERROR(1001,"年龄异常"),
    NAME_ERROR(1002,"名字异常");

    private Integer code;

    private String msg;


    SystemResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
