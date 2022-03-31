package com.example.springboot.model.bo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@ApiModel(description="教师实体类")
@JacksonXmlRootElement(localName = "Teacher")
public class Teacher {

    @JacksonXmlProperty(localName = "name")
    @Size(min = 1,max = 50)
    @ApiModelProperty("教师名字")
    private String name;

    @JacksonXmlProperty(localName = "sex")
    @Min(0)
    @Max(1)
    @ApiModelProperty("性别:0-F;1-M")
    private Integer sex;

}
