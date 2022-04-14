package com.example.springbootjpa.service;


import com.example.springboot.model.dto.local.sscsi.TVelBrakeBookingDto;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@CacheConfig(cacheNames = "velBrakeBooking")
@Repository
public interface TVelBrakeBookingRepository extends JpaRepository<TVelBrakeBookingDto,Integer> {


    TVelBrakeBookingDto findByBookingid(String bookId);

    @Cacheable
    @Query("from  TVelBrakeBookingDto v where v.plateno=:plateno")
    List<TVelBrakeBookingDto> findByName(@Param("plateno") String plateno);
}
