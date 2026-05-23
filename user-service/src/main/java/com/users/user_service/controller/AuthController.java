package com.users.user_service.controller;

import com.users.user_service.requestDto.LoginRequest;
import com.users.user_service.requestDto.RegisterRequest;
import com.users.user_service.responseDto.LoginResponse;
import com.users.user_service.responseDto.RegisterResponse;
import com.users.user_service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RegisterResponse signUp(@RequestBody RegisterRequest user) {
        logger.info("Sign up request comes......");
        return userService.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest user) {
        logger.info("Login request comes......");
        return userService.login(user);
    }
}
