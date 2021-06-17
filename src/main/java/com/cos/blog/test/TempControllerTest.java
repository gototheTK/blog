package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        // 파일리턴 기본경로: src/main/resources/static
        // 리턴명: /home.html
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        System.out.println("tempImg()");
        // 파일리턴 기본경로: src/main/resources/static
        // 리턴명: /02.jpg
        return "/02.jpg";
    }

}
