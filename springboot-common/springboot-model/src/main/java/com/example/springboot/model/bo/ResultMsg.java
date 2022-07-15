package com.example.springboot.model.bo;

import com.example.springboot.model.enums.HttpResult;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "ResultMsg")
@Data
@ApiModel(description = "返回结果")
public class ResultMsg<T> {
    //请求处理结果
    @ApiModelProperty("请求处理结果")
    @JacksonXmlProperty(localName = "flag")
    private Boolean flag;
    //返回代码
    @ApiModelProperty("返回代码")
    @JacksonXmlProperty(localName = "code")
    private Integer code;
    //提示消息
    @ApiModelProperty("提示消息")
    @JacksonXmlProperty(localName = "msg")
    private String msg;

    //返回内容集合
    @ApiModelProperty("返回内容")
    @JacksonXmlElementWrapper(localName = "list")
    @JacksonXmlProperty(localName = "entry")
    private List<T> data;

    public static <T> ResultMsg<T> success(Integer code, List<T> data, String msg){
        return new ResultMsg(Boolean.TRUE,code,data,msg);
    }

    public static <T> ResultMsg<T> success(Integer code, List<T> data){
        return new ResultMsg(Boolean.TRUE,code,data,"");
    }

    public static <T> ResultMsg<T> success(List<T> data){
        return new ResultMsg(Boolean.TRUE, HttpResult.S2000.getCode(),data,"");
    }

    public static <T> ResultMsg<T> success(Integer code, T data, String msg){
      return new ResultMsg(Boolean.TRUE,code,data,msg);
    }

    public static <T> ResultMsg<T> success(Integer code, T data){
        return new ResultMsg(Boolean.TRUE,code,data,"");
    }

    public static <T> ResultMsg<T> success(T data){
        return new ResultMsg(Boolean.TRUE, HttpResult.S2000.getCode(),data,"");
    }

    public static <T> ResultMsg<T> fail(Integer code, String msg){
        return new ResultMsg(Boolean.FALSE,code,null,msg);
    }



    private ResultMsg(Boolean flag, Integer code, T data, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data=new ArrayList<>();
        this.data.add(data);
    }


    public ResultMsg(Boolean flag, Integer code, List<T> data, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
