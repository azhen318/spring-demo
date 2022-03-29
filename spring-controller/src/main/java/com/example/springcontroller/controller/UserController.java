package com.example.springcontroller.controller;

import com.example.springcontroller.bo.ResultMsg;
import com.example.springcontroller.bo.User;
import com.example.springcontroller.enums.HttpResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    //换行符
    private static String lineSeparator = System.lineSeparator();

    static Map<Long,User> users= Collections.synchronizedMap(new HashMap<Long,User>());

    @ApiOperation(value = "查询所有用户")
    @GetMapping("/")
    public ResultMsg<User> getAllUser(){
        return ResultMsg.success(new ArrayList<User>(users.values()));
    }

    /**
     * 参数有效性验证
     * @param user
     * @param bindingResult
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/")
    public ResultMsg<String> insertUser(@RequestBody  @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            //校验结果以集合的形式返回，当然也可以获取单个。具体可以查看bindResult的API文档
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            //StringBuilder组装异常信息
            StringBuilder builder = new StringBuilder();
            //遍历拼装
            fieldErrorList.forEach(error -> {
                builder.append(error.getDefaultMessage() + lineSeparator);
            });
            builder.insert(0,"use @Valid n BingdingResult :" +lineSeparator);
            return ResultMsg.fail(HttpResultStatus.F400.getStatus(), builder.toString());
        }
        users.put(user.getId(),user);
        return ResultMsg.success("成功");
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/{id}")
    public ResultMsg<String> updateUser(@PathVariable Long id,@RequestBody User user){
        users.put(id,user);
        return ResultMsg.success("成功");
    }

    @ApiOperation(value = "查询用户")
    @GetMapping("/{id}")
    public ResultMsg<User> getUser(@PathVariable Long id){
        return ResultMsg.success(users.get(id));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public ResultMsg<String> delUser(@PathVariable Long id){
        users.remove(id);
        return ResultMsg.success("成功");
    }

}
