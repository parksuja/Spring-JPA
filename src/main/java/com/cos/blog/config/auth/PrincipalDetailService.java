package com.cos.blog.config.auth;


import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌 때, username,password 변수 2개를 가로챈다.
    //password 부분 처리는 알아서함
    // username이 DB에 있는지만 확인해주면 됨.

    @Override  //우리가 저장한 유저정보를 전달 할 수가없다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을수없어요."+username);
                });
        return new PrincipalDetail(principal); //시큐리티 세션에 유저 정보 저장
    }
}
