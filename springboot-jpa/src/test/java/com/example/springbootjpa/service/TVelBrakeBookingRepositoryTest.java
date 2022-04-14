package com.example.springbootjpa.service;

import com.example.springboot.model.dto.local.sscsi.TVelBrakeBookingDto;
import com.example.springboot.model.dto.local.sscsi.TestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TVelBrakeBookingRepositoryTest {

    @Autowired
    TVelBrakeBookingRepository velBrakeBookingRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    CacheManager cacheManager;

    @Test
    public void test(){
        List<TVelBrakeBookingDto> list=velBrakeBookingRepository.findAll();

        List<TVelBrakeBookingDto> list1=velBrakeBookingRepository.findByName("粤N02181");

        System.out.println("测试完成....");
    }

    /**
     * 事务测试
     */
    @Transactional(rollbackFor =Exception.class,propagation= Propagation.REQUIRED )
    @Test
    public void testTrans(){
        try {
            testRepository.saveAndFlush(new TestDto(22,"156464968789443133561664"));
            testRepository.saveAndFlush(new TestDto(23,"156464968789443133561664"));
            if(1>0)throw new Exception("1231");
            testRepository.saveAndFlush(new TestDto(24,"156464968789443133561664"));
            System.out.println("测试完成....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试缓存
     */
    @Test
    public void test2(){
        List<TVelBrakeBookingDto> list1=velBrakeBookingRepository.findByName("粤N02181");
        List<TVelBrakeBookingDto> list2=velBrakeBookingRepository.findByName("粤N02181");
        System.out.println("测试完成....");
    }

}
