package com.demo.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "index";
    }
}
