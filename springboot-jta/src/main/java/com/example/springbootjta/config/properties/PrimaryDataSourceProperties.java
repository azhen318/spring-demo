package com.example.springbootjta.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.primary")
public class PrimaryDataSourceProperties {

    private String url;

    private String username;

    private String password;

}
