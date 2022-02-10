package com.example.springbootredis.utils;

import java.util.concurrent.Semaphore;

public class SemaphoreUtils {

    public static Semaphore initSemaphore(Integer count){
        return new Semaphore(count,true);
    }

}
