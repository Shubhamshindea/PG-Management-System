package com.pgconnect.user.controller;

import com.pgconnect.user.dto.UserDto;
import com.pgconnect.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User Authentication and Management.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow frontend to connect
public class UserController {

    private final UserService userService;

    /**
     * API to register a new PG Owner or User.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto.AuthResponse> register(@Valid @RequestBody UserDto.RegistrationRequest request) {
        UserDto.AuthResponse response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * API to login.
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto.AuthResponse> login(@Valid @RequestBody UserDto.LoginRequest request) {
        UserDto.AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
