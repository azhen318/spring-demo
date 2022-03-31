package com.example.springboot.model.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheBean {

    private String key;
    private long ttl;
    private long maximumSize;
}
