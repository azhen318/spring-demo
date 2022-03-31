package com.example.springbootjpa.service;


import com.example.springboot.model.dto.local.sscsi.TestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository  extends JpaRepository<TestDto,Integer> {
}
