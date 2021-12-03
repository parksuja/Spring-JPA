package com.cos.blog.repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//DAO라고 생각하면 됨
//빈으로 등록 = spring IOC 객체를 가지고 있는지
//IOC 객체를 가지고 있어야지 인젝션을 통해서 DI 할 수 있음.
//@Repositroy 생갹가능
public interface BoardRepository extends JpaRepository<Board,Integer> {

}