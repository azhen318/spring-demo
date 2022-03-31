package com.example.springbootjta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.springboot.model.dto"})
public class SpringbootJtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJtaApplication.class, args);
    }

}
