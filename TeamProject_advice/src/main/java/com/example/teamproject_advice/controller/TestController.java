package com.example.teamproject_advice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/base")
    public String base() {
        return "home";
    }

    @GetMapping("/list")
    public String test() {
        return "board/list";
    }
    @GetMapping("/header")
    public String header() {
        return "fragment/header";
    }

}
