package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입
    private UserRepository userRepository;

    // save함수는 id를 전달하지않으면 insert를 해주고
    // save함수는 id를 전달하면 id에 대한 데이터가 없으면 update를 해주고
    // email, password 

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {

        try{
            userRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 Id는 DB에 없습니다.";
        }

        userRepository.deleteById(id);

        return id + "삭제성공";

    }

    @Transactional // 함수 종료시에 자동 commit
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        //json 데이터를 요청 => Java Object(MessageConverter의 Jackson라이브러리를 통해 )

        System.out.println("id : " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());


        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);
        // 더티 체킹
        return null;

    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=1, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        if(pagingUser.isLast()){
            return null;
        }

        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라미터를 전달 받을 수있음.
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될것아냐?
        // 그럼 return null이 리턴되자나.. 그럼 프로그램에 문제가 있지 않겠니?
        // Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!
        
        // // 람다식
        // User user = userRepository.findById(id).orElseThrow(()->{
        //     return new IllegalArgumentException("해당 사용자는 없습니다.");
        // });
        // return user;

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){

            @Override
            public IllegalArgumentException get(){
                return new IllegalArgumentException("해당하는 아이디가 존재하지 않습니다.");
            };

        });

        // 요청 : 웹브라우저
        // user 객체  = 자바 오브젝트
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 작동 
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에 응답해줍니다.
        return user;

    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body의 username, password, email  데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String joint(User user) { //key=value
        System.out.println(user.getId());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getRole());

        user.setRole(RoleType.USER);;
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }


}
