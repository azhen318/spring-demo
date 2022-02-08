package com.example.springbootredis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;

    private Integer port;

    private String password;

    private Integer timeout;

    private  RedisProperties.Jedis jedis=new Jedis();

    private Integer  database;

    @Getter
    @Setter
    public class Jedis{

        private Pool pool =new Pool();

        @Getter
        @Setter
        public class Pool{
            private Integer maxActive;
            private Duration maxWait;
            private Integer maxIdle;
            private Integer minIdle;
        }
    }
}
