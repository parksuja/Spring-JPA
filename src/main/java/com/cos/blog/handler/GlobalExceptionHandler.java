package com.cos.blog.handler;


import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice //Exception발생하면 이 클래스로 들어온다.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class) //IllegalArgumentException이 발생하면 해당 함수 값
//    e로 전달
    public ResponseDto<String> handleArgumentException(Exception e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }

}

