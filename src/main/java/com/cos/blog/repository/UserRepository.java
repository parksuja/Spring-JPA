package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//DAO라고 생각하면 됨
//빈으로 등록 = spring IOC 객체를 가지고 있는지
//IOC 객체를 가지고 있어야지 인젝션을 통해서 DI 할 수 있음.
//@Repositroy 생갹가능
public interface UserRepository extends JpaRepository<User,Integer> {
    //User테이블을 관리하고 primary key는 Integer 숫자
    //기본적인 CRUD extends해서 다 들고온다.
    
    //JPA Naming 쿼리
    // SELECT * FROM user WHERE username = ? AND password =?;
    User findByUsernameAndPassword(String username,String password);
}
