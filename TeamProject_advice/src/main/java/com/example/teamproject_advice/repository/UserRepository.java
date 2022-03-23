package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


// JPA Repository를 상속받아서 User Entity(class)와 연결 <이름, Id 타입>
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccountAndPassword(String account, String password);
    User findByAccount(String account);
    User findByPhoneNumber(String phoneNumber);
}