package com.example.springbootredis.controller;

import com.example.springbootredis.utils.JedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RestController("/")
public class PurchaseController {

    public String purchaseKey="p-key001";

    public Integer count=0;

    public Integer timeOut=30*1000;

    @Autowired
    JedisOperator jedisOperator;

    @Autowired
    RedissonClient redissonClient;

    ConcurrentLinkedQueue<String> qingGouQueue=new ConcurrentLinkedQueue<String>();

    /**
     * 百万秒杀实例
     * @param request
     * @return
     */
    @GetMapping("qingdan")
    public List<String> getQingDan(HttpServletRequest request){

        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        //抢到商品的用户
        List<String> shopUsers = new ArrayList<>();

        //构造很多用户
        ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<String>();
//        List<String> users = new ArrayList<>(110000);
        IntStream.range(0, 100000).parallel().forEach(b -> {
            queue.add("神牛-" + b);
        });

        count=10;

        queue.parallelStream().forEach(u ->{
//            String msg=qing(u);
            String msg=qingRedisson(u);
            if(msg !=null)
                shopUsers.add(msg);
        });

        stopWatch.stop();

        log.info("{}名用户参与了抢购,耗时{}",qingGouQueue.size(),stopWatch.getLastTaskTimeMillis());
        return shopUsers;
    }


    /**
     * 利用原生RedisClient 完成秒杀分布式锁
     * @param user
     * @return
     */
    private String qing(String user){

        Long t=System.currentTimeMillis();

        qingGouQueue.add(user);


        while (t+timeOut>=System.currentTimeMillis()){

            if(count<=0)break;

            if(jedisOperator.setnx(purchaseKey,user)){
                try {
                    log.info("{}获取到了key",user);
                    if(count==0)break;

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count-=1;

                    log.info("用户{}抢单成功跳出...所剩库存：{}", user, count);

                    return user + "抢单成功，所剩库存：" + count;
                } finally {
                    log.info("用户{}释放锁...",user);
                    jedisOperator.delnx(purchaseKey,user);
                }
            }
        }
        return null;
    }

    /**
     * 利用Redisson 完成秒杀分布式锁
     * @param user
     * @return
     */
    private String qingRedisson(String user){

        Long t=System.currentTimeMillis();

        qingGouQueue.add(user);


        while (t+timeOut>=System.currentTimeMillis()){

            if(count<=0)break;

            RLock lock = redissonClient.getLock(purchaseKey);

            try {
                Boolean lockF=lock.tryLock(1, TimeUnit.SECONDS);
                if(lockF){
                    log.info("{}获取到了key",user);
                    if(count==0)break;

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count-=1;

                    log.info("用户{}抢单成功跳出...所剩库存：{}", user, count);

                    return user + "抢单成功，所剩库存：" + count;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if( lock.isHeldByCurrentThread() && lock.isLocked()){
                    lock.unlock();
                    log.info("用户{}释放锁...",user);
                }
            }
        }
        return null;
    }

}
