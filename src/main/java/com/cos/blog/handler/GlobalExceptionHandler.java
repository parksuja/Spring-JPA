package com.cos.blog.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice //Exception발생하면 이 클래스로 들어온다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=IllegalArgumentException.class) //IllegalArgumentException이 발생하면 해당 함수 값
//    e로 전달
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>" +e.getMessage()+ "</h1>";
    }

}

