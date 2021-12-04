package com.cos.blog.service;


import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void 글쓰기(Board board, User user){  //title,content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    }

    @Transactional
    public void 글수정하기(int id,Board requestBoard){
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패");
                }); //영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setTitle(requestBoard.getContent());
        //해당 함수로 종료시 Service종료) 트랜잭션이 종료 됩니다, 더티체킹 -자동 업데이트가 flush (commit)
    }

    @Transactional
    public void 삭제하기(int id){
         boardRepository.deleteById(id);
    }
}
