package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

//    http://localhost:8000/blog/temp/home / 동적인 파일만 찾아준다.
    @GetMapping("/temp/home")
    public String tempHome(){
        return "/home.html";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp(){
        return "test";
    }
}
