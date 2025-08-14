package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.entity.UserEntity;
import com.rookies4.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//final이 붙은 생성자를 초기화 시켜준다.
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    //final이 붙은 생성자는 생성과 동시에 무조건 초기화해야한다
    private final UserRepository userRepository;


    //Constructor Injection
//    public UserRestController(UserRepository userRepository) {
//        System.out.println("생성자 injection : " + userRepository.getClass().getName());
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public UserEntity create(@RequestBody UserEntity user) {
        return userRepository.save(user);

    }

}
