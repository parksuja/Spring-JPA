package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//빈 생성자
//getter setter 같이 
//AllArgsConstructor 생성자까지 같이 만들어줌
public class Member {
   private  int id;
   private  String username;
   private  String password;
   private  String email;

     @Builder
//     생성자의 순서를 지키지 않아도 된다.필드의 값 상관없다
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
