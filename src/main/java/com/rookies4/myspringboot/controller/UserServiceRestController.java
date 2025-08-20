package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.controller.dto.UserDTO;
import com.rookies4.myspringboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserServiceRestController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO.UserResponse> create(
            @Valid @RequestBody UserDTO.UserCreateRequest request){
        UserDTO.UserResponse createdUser = userService.createUser(request);

        return ResponseEntity.ok(createdUser);
    }

}