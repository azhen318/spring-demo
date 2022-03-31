package com.example.springboot.model.bo;

import com.example.springboot.model.enums.HttpResultStatus;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private String code;
    //提示消息
    @ApiModelProperty("提示消息")
    @JacksonXmlProperty(localName = "msg")
    private String msg;
    //返回内容
    @ApiModelProperty("返回内容")
    @JacksonXmlProperty(localName = "value")
    private T data;

    //返回内容集合
    @ApiModelProperty("返回内容")
    @JacksonXmlElementWrapper(localName = "list")
    @JacksonXmlProperty(localName = "entry")
    private List<T> datas;

    public static <T> ResultMsg<T> success(String code, List<T> datas, String msg){
        return new ResultMsg(Boolean.TRUE,code,datas,msg);
    }

    public static <T> ResultMsg<T> success(String code, List<T> datas){
        return new ResultMsg(Boolean.TRUE,code,datas,"");
    }

    public static <T> ResultMsg<T> success(List<T> datas){
        return new ResultMsg(Boolean.TRUE, HttpResultStatus.S200.getStatus(),datas,"");
    }

    public static <T> ResultMsg<T> success(String code, T data, String msg){
      return new ResultMsg(Boolean.TRUE,code,data,msg);
    }

    public static <T> ResultMsg<T> success(String code, T data){
        return new ResultMsg(Boolean.TRUE,code,data,"");
    }

    public static <T> ResultMsg<T> success(T data){
        return new ResultMsg(Boolean.TRUE, HttpResultStatus.S200.getStatus(),data,"");
    }

    public static <T> ResultMsg<T> fail(String code, String msg){
        return new ResultMsg(Boolean.FALSE,code,null,msg);
    }


    public static <T> ResultMsg<T> fail(String msg){
        return new ResultMsg(Boolean.FALSE,HttpResultStatus.F500.getStatus(), null,msg);
    }


    private ResultMsg(Boolean flag, String code, T data, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public ResultMsg(Boolean flag, String code, List<T> datas, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }
}
