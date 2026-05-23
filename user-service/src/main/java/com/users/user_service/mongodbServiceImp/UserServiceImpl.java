package com.users.user_service.mongodbServiceImp;

import com.users.user_service.model.User;
import com.users.user_service.requestDto.LoginRequest;
import com.users.user_service.requestDto.RegisterRequest;
import com.users.user_service.responseDto.LoginResponse;
import com.users.user_service.responseDto.RegisterResponse;
import com.users.user_service.ropository.UserRepository;
import com.users.user_service.security.JwtService;
import com.users.user_service.service.UserService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest user) {
        if (userRepository.existsByName(user.name()) && userRepository.existsByPassword(user.password())) {
            return new RegisterResponse("User already exists");
        }

        User newUser = new User(null, user.name(), user.password());
        userRepository.save(newUser);

        return new RegisterResponse("User registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest user) {
        User existingUser = userRepository.findByName(user.name());

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        if (!existingUser.password().equals(user.password())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(existingUser.id());

        return new LoginResponse(token);
    }
}
