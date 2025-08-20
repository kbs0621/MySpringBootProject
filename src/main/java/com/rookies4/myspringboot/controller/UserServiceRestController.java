package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.controller.dto.UserDTO;
import com.rookies4.myspringboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.UserResponse> getUserById(Long id){
        UserDTO.UserResponse userById = userService.getUserById(id);
        return ResponseEntity.ok(userById);
    }

}