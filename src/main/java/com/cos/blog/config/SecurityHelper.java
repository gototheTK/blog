package com.cos.blog.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import com.cos.blog.model.RoleType;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

public class SecurityHelper {

    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();


    private Authentication authentication;

    public SecurityHelper(SecurityContext context) {
        authentication = context.getAuthentication();
        if (authentication != null) {
            authorities = authentication.getAuthorities();
        }
    }

    public boolean isAuthenticated() {
        return authentication == null ? false : authentication.isAuthenticated();
    }

    public Object getPrincipal() {
        return authentication.getPrincipal();
    }

    public boolean isAnonymous() {
        return authentication == null ? true : authentication instanceof AnonymousAuthenticationToken;
    }

    public String username() {
        return authentication == null ? "" : authentication.getName();
    }

    public boolean isAdminOrSuper() {
        return hasAnyRole(Arrays.asList(RoleType.ADMIN));
    }

    public boolean hasRole(RoleType role) {
        return authorities == null ? false
                : authorities.stream().anyMatch(authority -> authority.getAuthority().equals(role.name()));
    }

    public boolean hasAnyRole(List<RoleType> roles) {
        return roles.stream().anyMatch(role -> hasRole(role));
    }

}
