package com.rocks.lovable_clone.service;

import com.rocks.lovable_clone.dto.auth.AuthResponse;
import com.rocks.lovable_clone.dto.auth.LoginRequest;
import com.rocks.lovable_clone.dto.auth.SignupRequest;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
