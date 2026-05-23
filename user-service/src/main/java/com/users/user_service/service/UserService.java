package com.users.user_service.service;

import com.users.user_service.requestDto.LoginRequest;
import com.users.user_service.requestDto.RegisterRequest;
import com.users.user_service.responseDto.LoginResponse;
import com.users.user_service.responseDto.RegisterResponse;

public interface UserService {
    public RegisterResponse register(RegisterRequest user);
    public LoginResponse login(LoginRequest user);
}
