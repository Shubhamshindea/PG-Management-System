package com.pgconnect.user.service;

import com.pgconnect.user.dto.UserDto;

/**
 * Service interface for User related operations.
 */
public interface UserService {
    
    /**
     * Registers a new user.
     * @param request Registration details.
     * @return AuthResponse containing token and basic info.
     */
    UserDto.AuthResponse register(UserDto.RegistrationRequest request);

    /**
     * Authenticates a user.
     * @param request Login details.
     * @return AuthResponse containing token and basic info.
     */
    UserDto.AuthResponse login(UserDto.LoginRequest request);
}
