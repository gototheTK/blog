package com.cos.blog.repository;

import java.util.Optional;

import com.cos.blog.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean등록이 된다.
// @Repository // 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

    // Select * from user where username = 1?
    Optional<User> findByUsername(String username);


    // JPA Naming 쿼리
    // SELECT * FROM user WHERE username = ? AND password = ?
    // User findByUsernameAndPassword(String username, String password);

    // @Query(value = "SELECT * FROM user WHERE username = ? AND password = ?",
    // nativeQuery = true)
    // User login(String username, String password);

    

}
