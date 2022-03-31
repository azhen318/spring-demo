package com.example.springbootjta.service.mcs;

import com.example.springboot.model.dto.local.mcs.AddressDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<AddressDto,Integer> {
}
