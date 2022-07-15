package com.example.springboot.model.exception;

import com.example.springboot.model.enums.SystemResult;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private Integer code;

    private String msg;


    public ApiException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
        this.code= SystemResult.ERROR.getCode();
    }
}
