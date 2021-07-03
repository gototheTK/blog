package com.cos.blog.config;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public class SecurityExpress extends SecurityExpressionRoot{

    public SecurityExpress(Authentication authentication) {
        super(authentication);
        //TODO Auto-generated constructor stub
    }
    
    public SecurityExpress(SecurityContext context){
        this(context.getAuthentication());
    }
    
}
