package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.entity.UserEntity;
import com.rookies4.myspringboot.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/users/signup")
    public String showSignUpForm(@ModelAttribute("user") UserEntity user) {

        return "add-user";
    }

    @PostMapping("/users/adduser")
    public String addUser(@Valid @ModelAttribute("user") UserEntity user,
                          BindingResult result, Model model) {
        //검증 오류 발생한 경우
        if(result.hasErrors()){
            return "add-user";
        }
        //등록 요청
        userRepository.save(user);
        //model.addAttribute("users", userRepository.findAll());

        // 등록 후 조회로 돌아감
        //return "index";
        //redirect:path path를 지정해줌
        return "redirect:/users/index";
    }

    @GetMapping("/users/index")
    public ModelAndView index() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return new ModelAndView("index", "users", userEntityList);
    }

    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name", "스프링부트");
        return "leaf";
    }
}