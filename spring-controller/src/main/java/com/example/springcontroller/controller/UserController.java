package com.example.springcontroller.controller;

import com.example.springboot.model.bo.ResultMsg;
import com.example.springboot.model.bo.User;
import com.example.springboot.model.enums.HttpResult;
import com.example.springboot.model.enums.SystemResult;
import com.example.springboot.model.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    //换行符
    private static String lineSeparator = System.lineSeparator();

    static Map<Long, User> users= Collections.synchronizedMap(new HashMap<Long,User>());

    @ApiOperation(value = "查询所有用户")
    @GetMapping("/")
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        list.addAll(users.values());
        return list;
    }

    /**
     * 参数有效性验证
     * @param user
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/")
    public String insertUser1(@RequestBody @Validated User user){
        users.put(user.getId(),user);
        return "新增成功";
    }

    /**
     * AOP 验证参数；统一返回格式
     * @param user
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/addUser")
    public User insertUser(@RequestBody @Validated User user){

        if(user.getBirDay().after(new Date()))
            throw new ApiException(SystemResult.AGE_ERROR.getCode(), "出生日期大于当前时间");
        if(user.getName().length()>10)
            throw new ApiException(SystemResult.NAME_ERROR.getCode(), "名字长度大于10");

        users.put(user.getId(),user);
        return user;
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

    @ApiOperation(value = "测试统一返回字符串格式")
    @GetMapping("/test/{msg}")
    public String testReturnString(@PathVariable String msg){
        return msg;
    }

}
