package com.example.springboot.model.bo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@ApiModel(description = "用户实体")
public class User {

    @ApiModelProperty("用户编号")
    private Long id;


    @NotNull(message = "用户名不能为空")
    @ApiModelProperty("用户名字")
    private String name;

//    @NotNull
//    @ApiModelProperty("用户年龄")
//    private Integer age;

    @Email(message = "邮箱格式不对")
    @ApiModelProperty("邮箱地址")
    private String mail;

    @Past
    @NotNull(message = "出生日期不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("用户出生日期：格式yyyy-MM-dd")
    private Date  birDay;
}
