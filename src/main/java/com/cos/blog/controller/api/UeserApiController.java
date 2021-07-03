package com.cos.blog.controller.api;


import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UeserApiController {

    @Autowired
    private UserService userService;

    

    // @Autowired
    // HttpSession session;

    @PostMapping("/auth/join")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController:save호출됨");

        
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    // @PostMapping("/api/login")
    // public ResponseDto<Integer> login(@RequestBody User user, HttpSession
    // session) {
    // System.out.println("로그인");

    // User principal = userService.로그인(user);// principal 주체

    // if (principal != null) {
    // session.setAttribute("principal", principal);
    // }
    // return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    // }

}
