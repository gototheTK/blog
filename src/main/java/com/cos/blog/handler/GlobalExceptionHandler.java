package com.cos.blog.handler;


import javax.servlet.http.HttpServletRequest;

import com.cos.blog.config.SecurityExpress;
import com.cos.blog.config.SecurityHelper;
import com.cos.blog.dto.ResponseDto;

import org.springframework.http.HttpStatus;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private SecurityHelper securityHelper;
    
    private SecurityExpress express;

    @ExceptionHandler(value=Exception.class)
    public ResponseDto<String> handleArgumentExceoption(Exception e) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }


    @ModelAttribute
    public void addDefaultAttribues(HttpServletRequest request, Model model) {
        securityHelper = new SecurityHelper(SecurityContextHolder.getContext());
        express= new SecurityExpress(SecurityContextHolder.getContext());

        System.out.println("테스트: " + express.getAuthentication());

        model.addAttribute("isLoggedIn", securityHelper.isAuthenticated() && !securityHelper.isAnonymous());
        model.addAttribute("username", securityHelper.username());
        model.addAttribute("isAdminOrSuper", securityHelper.isAdminOrSuper());


    }


}
