# 说明

## redis

### springboot redis使用

### jedis分布式锁

* jedis配置redisCfg
* jedis操作类
* jedis百万秒杀实例[参考地址:](https://mp.weixin.qq.com/s/As89IMubVHn2BH5R6dU1Hw)
  >jedis.set(key, val, "NX", "PX", 1000 * 60) 分布式锁  
  > NX:存在即插入  
  > PX:设置失效时间
  > 
### redisson分布式锁
* redissonClient配置
* redissonClient重入锁实现分布式锁
* redissonClient[参考地址:](https://github.com/redisson/redisson/wiki)

### Semaphor信号量
*使用场景：
 >资源有限，请求者同时只能定量；  
 >如：一个机房只有三台打印机，只能同时三个人打印 

## ManageCache

* 配置类 CacheCfg
  > @Cacheable  多用于查询方法   
  > @CachePut   多用于更新保存方法，更新缓存  
  > @CacheEvict 多用户删除方法 
* redis 与 ManageCache
* 高性能缓存 CaffeineCache
    > 性能高于google gvauva

