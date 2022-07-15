package com.example.springboot.model.enums;

import com.example.springboot.model.inter.StatusCode;
import lombok.Getter;

@Getter
public enum HttpResult implements StatusCode {
    S2000(2000,"成功"),
    S2001(2001,"参数异常");

    private Integer code;

    private String msg;


    HttpResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
