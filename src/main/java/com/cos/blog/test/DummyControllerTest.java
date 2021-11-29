package com.cos.blog.test;


import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController  //data를 리턴해주는 controller
public class DummyControllerTest {

    @Autowired //DummyControllerTest가 메모리에 올라갈때 같이 올라가도록 (의존성주입 DI)
//    ,UserRepository타입으로 Spring이 관리하는 객체가 있다면 userRepositroy변수에 넣는다.
    //UserRepository 인터페이스 메모리에 띄워준다. 그냥 사용만 하면 된다.
    private UserRepository userRepository;
    //Spring이 RestController를 읽어서 DummyControllerTest를 메모리에 띄워줄때 userRepository null

// save 함수는 id를 전달하지 않으면 insert를 해주고 ,
// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 UPDATE
// 없으면 insert
    @Transactional   //save 호출없이 변경
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id: " +id);
        System.out.println("password:" +requestUser.getPassword());
        System.out.println("email:" +requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //더티체킹
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다.id" + id;
    }


    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }


    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        //1페이지당 2개 id 내림차순 정렬
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    //{id}주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    //user/4가 없으면 user가 null이 된다. return null이 되니 문제가 된다.
//  Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return
    public User detail(@PathVariable int id){

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저가 없습니다.");
            }
        });

//        //람다식 사용
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });
        //요청 : 웹브라우저 , user 객체 = 자바 오브젝트
        //변환 (웹브라우저가 이해할 수 있는 데이터) -> json
        //스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        //자바 오브젝트 리턴하게 되면 MessageConverter가 Jackson 라이브러리 호출
        //user오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
                return user;
    }

    //http://localhost:8000/blog/dummy/join(요청)req
    //http의 body에 username,password,email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user){ //User object로 받는다.
        //key & value 형태를 받아준다.
        System.out.println("id:" + user.getId());
        System.out.println("username:" +user.getUsername());
        System.out.println("password:" +user.getPassword());
        System.out.println("Email:" +user.getEmail());

        user.setRole(RoleType.USER);
        System.out.println(user);
        userRepository.save(user);  //role이 insert 들어가서 null이 들어감
        return "회원가입이 완료되었습니다.";
    }
}
