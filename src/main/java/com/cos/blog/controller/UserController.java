package com.cos.blog.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/joinForm")
    public String jointForm() {

        return "/user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {

        return "/user/loginForm";
    }

}
