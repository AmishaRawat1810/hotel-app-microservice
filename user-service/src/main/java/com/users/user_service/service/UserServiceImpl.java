package com.users.user_service.service;

import com.users.user_service.dto.AuthDTOs;
import com.users.user_service.model.User;
import com.users.user_service.repository.UserRepository;
import com.users.user_service.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AuthDTOs.RegisterResponse register(AuthDTOs.RegisterRequest user) {
        if (userRepository.existsByName(user.name()) && userRepository.existsByPassword(user.password())) {
            return new AuthDTOs.RegisterResponse("User already exists");
        }

        User newUser = new User(null, user.name(), user.password());
        userRepository.save(newUser);

        return new AuthDTOs.RegisterResponse("User registered successfully");
    }

    @Override
    public AuthDTOs.LoginResponse login(AuthDTOs.LoginRequest user) {
        User existingUser = userRepository.findByName(user.name());

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!existingUser.password().equals(user.password())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(existingUser.id());

        return new AuthDTOs.LoginResponse(token);
    }
}
