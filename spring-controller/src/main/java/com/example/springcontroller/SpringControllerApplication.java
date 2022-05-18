package com.example.springcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class} )
public class SpringControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringControllerApplication.class, args);
    }

}
