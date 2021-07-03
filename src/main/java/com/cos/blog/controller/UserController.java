package com.cos.blog.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출힙할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.html
// static이하에 있는 /js/**, css/**, /images/**

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String jointForm() {

        return "/user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {

        return "/user/loginForm";
    }

}
