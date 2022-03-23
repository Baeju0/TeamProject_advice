package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PartnerRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
//        insert info user values(id, account);
        Partner part1 = new Partner();

        part1.setId(1L);
        part1.setName("JYB");
        part1.setStatus("ready");
        part1.setAddress("대전 용문");
        part1.setCallCenter("010-1234-5678");
        part1.setPartnerNumber("010-1111-1111");
        part1.setBusinessNumber("1234567891011121");
        part1.setCeoName("배쥬용");

        part1.setRegisteredAt(LocalDateTime.now());

        part1.setCreatedAt(LocalDateTime.now());
        part1.setCreatedBy("admin01");

        part1.setCategoryId(1L);

        Partner newPartner = partnerRepository.save(part1);
        System.out.println(newPartner);

        // insert into user values(id, account, ...)

    }

//    @Test
//    public void read() {
//
//        List<User> userList = userRepository.findAll();
//
//        for (User user : userList) {
//            user.toString();
//        }
//    }
//
//    @Test
//    public void update() {
//        Optional<User> user = userRepository.findById(5L);
//
//        user.ifPresent(u -> {
//            u.setAccount("juyoung");
//            u.setPassword("skdkskdk");
//            u.setUpdatedAt(LocalDateTime.now());
//            u.setUpdatedBy("admin");
//
//            userRepository.save(u);
//
//        });
//
//
//
//    }
//
//    @Test
//    public void delete() {
//        Optional<User> user = userRepository.findById(6L);
//
//        user.ifPresent(u -> {
//            userRepository.delete(u);
//        });
//    }
//
}