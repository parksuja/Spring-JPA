package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne  //여러개의 댓글은 하나의 게시물에존재
    @JoinColumn(name="boardId")
    private Board board; //Board테이블에 id 참조해서 int값으로 나타남


    @ManyToOne //여러개의 댓글을 하나의 유저가 쓸 수 있음
    @JoinColumn(name="userId")
    private User user;  //User테이블에 id 참조해서 int값으로 나타남



    @CreationTimestamp
    private Timestamp createDate;
}
