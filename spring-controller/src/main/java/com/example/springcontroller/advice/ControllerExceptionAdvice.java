package com.example.springcontroller.advice;

import com.example.springboot.model.bo.ResultMsg;
import com.example.springboot.model.enums.HttpResult;
import com.example.springboot.model.exception.ApiException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Controller 异常处理中心
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 请求参数符合性验证
     * @param bindException
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultMsg MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException bindException){

        String msg = bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultMsg.fail(HttpResult.S2001.getCode(), msg);
    }


    @ExceptionHandler({ApiException.class})
    public ResultMsg BussValidException(ApiException apiException){
        return ResultMsg.fail(apiException.getCode(),apiException.getMsg());
    }


}
