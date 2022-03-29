package com.example.springcontroller.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.example")
public class AxampleProperties {

    /**
     * 测试ID
     */
    private Long id;

    /**
     * 测试名字
     */
    private String name;

}
