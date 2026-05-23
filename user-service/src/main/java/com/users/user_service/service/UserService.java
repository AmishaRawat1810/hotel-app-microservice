package com.users.user_service.service;

import com.users.user_service.requestDto.RegisterRequest;
import com.users.user_service.responseDto.RegisterResponse;
import org.springframework.stereotype.Service;

public interface UserService {
    public RegisterResponse register(RegisterRequest user);
}
