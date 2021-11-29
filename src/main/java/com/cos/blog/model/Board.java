package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob //대용량 데이터 쓸때 사용
    private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됩니다.

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) //Many = Board , User = One 한명의 유저는 여러개의 게시물 쓸 수 있다.
    @JoinColumn(name="userId") //이름이 userId로 들어간다.
    private User user; //DB는 오브젝트를 저장할 수 없다. fk 자바는 오브젝트를 저장 할 수 있다.


    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //하나의 게시물은 여러개의 댓글 필요
    //mappedBy는 연관관계의 주인이 아니다. (FK키가 아니다) DB에 컬럼 만들지 마라
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;


}
