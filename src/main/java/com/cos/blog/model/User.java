package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


//테이블화시키기위해
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //null인 insert 제외시켜줍니다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
//  primary키가 된다.
    private int id;


    @Column(nullable=false,length=30,unique=true)
//   null이 될 수 없고 30자 이하
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

//    @ColumnDefault("'user'") //Default값 user로 role 설정
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다. //ADMIN,USER

    @CreationTimestamp
//    시간이 자동으로 입력 (insert가 될때)
    private Timestamp createDate;

}
