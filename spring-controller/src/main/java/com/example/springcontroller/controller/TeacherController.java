package com.example.springcontroller.controller;

import com.example.springboot.model.bo.ResultMsg;
import com.example.springboot.model.bo.Teacher;
import com.example.springboot.model.enums.HttpResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags="教师管理")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    //换行符
    private static String lineSeparator = System.lineSeparator();

    static Map<String, Teacher> teachers= Collections.synchronizedMap(new HashMap<String,Teacher>());

    /**
     * xml内容交互
     * @param teacher
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "教师新增;请求入参返回都是xml")
    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResultMsg<Teacher> creater(@RequestBody @Valid Teacher teacher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //校验结果以集合的形式返回，当然也可以获取单个。具体可以查看bindResult的API文档
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            //StringBuilder组装异常信息
            StringBuilder builder = new StringBuilder();
            //遍历拼装
            fieldErrorList.forEach(error -> {
                builder.append(error.getDefaultMessage() + lineSeparator);
            });
            builder.insert(0, "use @Valid n BingdingResult :" + lineSeparator);
            return ResultMsg.fail(HttpResultStatus.F400.getStatus(), builder.toString());
        }
        teachers.put(teacher.getName(), teacher);
        return ResultMsg.success(teacher);
    }

    /**
     * xml内容交互
     * @return
     */
    @ApiOperation(value = "查询所有教师;请求入参返回都是xml")
    @GetMapping(value = "/getAll",
//            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ResultMsg<Teacher> getAll() {
        return ResultMsg.success(new ArrayList<Teacher>(teachers.values()));
    }


}
