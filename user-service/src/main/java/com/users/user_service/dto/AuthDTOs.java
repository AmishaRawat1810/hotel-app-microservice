package com.users.user_service.dto;

public class AuthDTOs {
    public record RegisterRequest(String name, String password){}
    public record LoginRequest(String name, String password){}
    public record RegisterResponse(String message){}
    public record LoginResponse(String token){}
}
