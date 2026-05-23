package com.users.user_service.controller;

import com.users.user_service.requestDto.RegisterRequest;
import com.users.user_service.responseDto.RegisterResponse;
import com.users.user_service.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RegisterResponse signUp(@RequestBody RegisterRequest user) {
        return userService.register(user);
    }
}
