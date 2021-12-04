package com.cos.blog.controller.api;


import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {//username,password,email

        System.out.println("save호출됨");
        //실제로 DB에 insert를 하고 아래에서 return이 되면 됩니다.
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) { //json데이터는 requestbody 붙여준다.
        userService.회원수정(user);
        //여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 됐음.
        //하지만 세션값은 변경 X
        //세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user , HttpSession session){
//        System.out.println("login호출됨");
//
//        User principal = userService.로그인(user);  //principal(접근주체)
//
//        if(principal != null){
//            session.setAttribute("principal", principal);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }



