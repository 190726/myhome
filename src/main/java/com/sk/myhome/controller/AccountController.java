package com.sk.myhome.controller;

import com.sk.myhome.model.User;
import com.sk.myhome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/register")
    public String register(){
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/";
    }


}
