package com.example.springboot.freemarker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootFreemarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFreemarkerApplication.class, args);
    }

}
