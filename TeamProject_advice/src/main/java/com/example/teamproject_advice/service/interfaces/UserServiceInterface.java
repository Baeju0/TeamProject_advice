package com.example.teamproject_advice.service.interfaces;

import com.example.teamproject_advice.model.entity.User;

import java.util.List;

public interface UserServiceInterface {

    List<String> clotheStyleList();     // 선호하는 옷 스타일 리스트
    User registerUser(User user);       // update spring security

    boolean isUser(String account, String password);
    boolean isAccount(String account);
    boolean isPhoneNumber(String phoneNumber);
}
