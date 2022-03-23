package com.example.teamproject_advice.controller;


import com.example.teamproject_advice.model.entity.User;
import com.example.teamproject_advice.service.implement.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    // 로그인 창
    @GetMapping("/login")
    public String login(String error, String logout, Model model) {
        if ( error != null ) { model.addAttribute("error", true); }
        if ( logout != null ) { model.addAttribute("logout", "로그아웃"); }
        return "/user/login";
    }

//    // 로그인 요청
//    @PostMapping("/loginAction.do")
//    public String loginAction(@RequestParam(value = "username") String account,
//                              @RequestParam(value = "password") String password) {
//
//        if ( service.isUser(account, password) ) { return "/home"; }
//        else { return "redirect:/user/login"; }
//    }

    // spring security
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("clotheStyleList", service.clotheStyleList());
        return "user/register";
    }


    // 회원가입 창
    @PostMapping("/register")
    public String register(Model model, String error,
                           String account, String password, String phone,
                           String gender, String color, String myTemp,
                           String clotheStyle) {

        if ( service.isAccount(account) ) { return "redirect:/register?error"; }

        User user = User.builder()
                .account(account)
                .password(password)
                .phoneNumber(phone)
                .gender(gender)
                .color(color)
                .myTemp(myTemp)
                .clotheStyle(clotheStyle)
                .build();

        service.registerUser(user);
        return "redirect:/";
    }



    // 회원가입 요청
    @PostMapping("/registerAction.do")
    public String registerAction(String account, String pw, String phone,
                                 String gender, String color, String myTemp,
                                 String clotheStyle) {

        if ( service.isUser(account, pw) ||
                service.isAccount(account) ||
                service.isPhoneNumber(phone)) { return "redirect:/user/register"; }

        User user = User.builder()
                .account(account)
                .password(pw)
                .phoneNumber(phone)
                .gender(gender)
                .color(color)
                .myTemp(myTemp)
                .clotheStyle(clotheStyle)
                .build();

        service.registerUser(user);

        return "redirect:/user/login";

    }

}
