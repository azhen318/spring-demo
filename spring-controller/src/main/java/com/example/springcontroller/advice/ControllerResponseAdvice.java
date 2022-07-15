package com.example.springcontroller.advice;

import com.example.springboot.model.bo.ResultMsg;
import com.example.springboot.model.exception.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = {"com.example.springcontroller.controller"})
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().isAssignableFrom(ResultMsg.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(returnType.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper=new ObjectMapper();

            try {
                return objectMapper.writeValueAsString(ResultMsg.success(body));
            } catch (JsonProcessingException e) {
                throw new ApiException(e.getMessage());
            }
        }
        return ResultMsg.success(body);
    }
}
