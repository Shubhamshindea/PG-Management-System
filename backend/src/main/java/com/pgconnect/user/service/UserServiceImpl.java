package com.pgconnect.user.service;

import com.pgconnect.user.dto.UserDto;
import com.pgconnect.user.entity.User;
import com.pgconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementation of UserService.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    
    // In a real application, we would use PasswordEncoder and JwtUtil
    // private final PasswordEncoder passwordEncoder;
    // private final JwtUtil jwtUtil;

    @Override
    public UserDto.AuthResponse register(UserDto.RegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                // .passwordHash(passwordEncoder.encode(request.getPassword()))
                .passwordHash("hashed_" + request.getPassword()) // Mock hashing for now
                .role(request.getRole())
                .build();

        user = userRepository.save(user);

        // String token = jwtUtil.generateToken(user);
        String token = "mock_jwt_token_for_" + user.getId();

        return createAuthResponse(user, token);
    }

    @Override
    public UserDto.AuthResponse login(UserDto.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials!"));

        // if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
        if (!user.getPasswordHash().equals("hashed_" + request.getPassword())) { // Mock check
            throw new RuntimeException("Invalid credentials!");
        }

        // String token = jwtUtil.generateToken(user);
        String token = "mock_jwt_token_for_" + user.getId();

        return createAuthResponse(user, token);
    }

    private UserDto.AuthResponse createAuthResponse(User user, String token) {
        UserDto.AuthResponse response = new UserDto.AuthResponse();
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());
        return response;
    }
}
