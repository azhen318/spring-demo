package com.example.springboot.model.enums;

public enum HttpResultStatus {
    S200("200","成功"),
    F400("400","请求异常"),
    F500("500","服务异常");

    private String status;

    private String desc;


    HttpResultStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
