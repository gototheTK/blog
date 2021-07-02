package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/user/**").authenticated().antMatchers("/manager/**")
                .access("hasRole('MANAGER') or hasRole('ADMIN')").antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().permitAll().and().formLogin().loginPage("/loginForm").loginProcessingUrl("/api/login")
                .defaultSuccessUrl("/");
    }

}
