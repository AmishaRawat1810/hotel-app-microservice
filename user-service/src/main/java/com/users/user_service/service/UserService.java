package com.users.user_service.service;

import com.users.user_service.dto.AuthDTOs;

public interface UserService {
    String findUserNameById(String id);
    String findIdByToken(String token);
    AuthDTOs.RegisterResponse register(AuthDTOs.RegisterRequest user);
    AuthDTOs.LoginResponse login(AuthDTOs.LoginRequest user);
}
