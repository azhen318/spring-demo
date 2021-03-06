package com.example.springbootredis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisOperator {
    @Autowired
    StringRedisTemplate redisTemplate;


    public void set(String key,String val){
        redisTemplate.opsForValue().set(key,val);
    }

}
