package com.example.springbootredis.cfg;

import com.example.springbootredis.properties.RedisProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



@Configuration
public class RedisCfg {

    @Autowired
    RedisProperties redisProperties;


    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        jedisPoolConfig.setMaxWait(redisProperties.getJedis().getPool().getMaxWait());
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig){
        return new JedisPool(jedisPoolConfig,redisProperties.getHost(),
                        redisProperties.getPort(),redisProperties.getTimeout(),
                        redisProperties.getPassword(),redisProperties.getDatabase());
    }

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().
                setAddress("redis://"+redisProperties.getHost()+":"+redisProperties.getPort()).
                setPassword(redisProperties.getPassword()).
                setDatabase(redisProperties.getDatabase());
        return Redisson.create(config);
    }

}
