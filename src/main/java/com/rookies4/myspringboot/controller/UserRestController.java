package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.entity.UserEntity;
import com.rookies4.myspringboot.exception.BusinessException;
import com.rookies4.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController-> JSON의 형식으로 보내준다.
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

    //Post에 등록
    @PostMapping
    public UserEntity create(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    //Post에 등록된 DB 전체 조회하기
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    //Post에 등록된 DB ID로 조회하기
    //@GetMapping("/{}") {}는 동적 변수를 받기 위해서 ex) /api/users/1 , /api/users/2
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN','ROLE_USER')")
    public UserEntity getUser(@PathVariable Long id) {
        UserEntity existUser = getExistEntity(id);
        return existUser;
    }

    //Email로 조회하고, 수정하기
    @PatchMapping("/{email}/")
    public UserEntity updateUser(@PathVariable String email, @RequestBody UserEntity userDetail) {
        UserEntity existUser = userRepository.findByEmail(email) //Optioanl<UserEntity>
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        existUser.setName(userDetail.getName());
        UserEntity updateUser = userRepository.save(existUser);
        return updateUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserEntity existUser = getExistEntity(id);

        userRepository.delete(existUser);
        return ResponseEntity.ok("User가 삭제 되었습니다,");
    }

    private UserEntity getExistEntity(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);

        UserEntity existUser = optionalUser
                .orElseThrow(() -> new BusinessException("User Not Found"));
        return existUser;
    }
    //인증 없이 접근 가능한 경로
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }
}
