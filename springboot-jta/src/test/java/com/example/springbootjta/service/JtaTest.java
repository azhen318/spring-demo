package com.example.springbootjta.service;

import com.example.springboot.model.dto.local.mcs.AddressDto;
import com.example.springboot.model.dto.local.sscsi.TestDto;
import com.example.springbootjta.service.mcs.AddressRepository;
import com.example.springbootjta.service.sscsi.TestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * 跨数据库事务
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JtaTest {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TestRepository testRepository;

    /**
     * 无JTA事务
     */
    @Test
    public void test(){

        try {
            addressRepository.save(new AddressDto(104,"12316"));
            if(1>0) throw new Exception("骗你了...");
            testRepository.save(new TestDto(104,"12316"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("测试完成....");

    }

    /**
     * 添加JTA事务
     */
    @Transactional
    @Test
    public void test2(){
        try {
            addressRepository.save(new AddressDto(103,"12316"));
            if(1>0) throw new Exception("骗你了...");
            testRepository.save(new TestDto(103,"12316"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("测试完成....");

    }

}
