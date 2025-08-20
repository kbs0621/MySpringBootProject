package com.rookies4.myspringboot.service;

import com.rookies4.myspringboot.controller.dto.UserDTO;
import com.rookies4.myspringboot.entity.UserEntity;
import com.rookies4.myspringboot.exception.BusinessException;
import com.rookies4.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    //User 등록
    @Transactional
    public UserDTO.UserResponse createUser(UserDTO.UserCreateRequest reqeust) {
        //Email 중복검사 BizException 발생 시키고 종료
        userRepository.findByEmail(reqeust.getEmail()) //Optional<UserEntity>
                .ifPresent(entity -> {
                    throw new BusinessException("User with this Email already Exist", HttpStatus.CONFLICT);
                });
        //DTO => Entity 로 변환
        UserEntity entity = reqeust.toEntity();
        UserEntity savedEntity = userRepository.save(entity);
        //Entity => DTO로 변환 후 리턴됨
        return new UserDTO.UserResponse(savedEntity);
    }

    //Id로 User 조회하기
    public UserDTO.UserResponse getUserById(Long id){
        UserEntity userEntity = getUserExist(id);
        return new UserDTO.UserResponse(userEntity);
    }

    //내부 Helper Method
    private UserEntity getUserExist(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
    }
}
