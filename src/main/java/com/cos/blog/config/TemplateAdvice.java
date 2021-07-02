package com.cos.blog.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class TemplateAdvice {

    private SecurityHelper securityHelper;

    @ModelAttribute
    public void addDefaultAttribues(HttpServletRequest request, Model model) {
        securityHelper = new SecurityHelper(SecurityContextHolder.getContext());

        model.addAttribute("isLoggedIn", securityHelper.isAuthenticated() && !securityHelper.isAnonymous());
        model.addAttribute("username", securityHelper.username());
        model.addAttribute("isAdminOrsuper", securityHelper.isAdminOrSuper());
        model.addAttribute("principal", securityHelper.getPrincipal());
    }

}