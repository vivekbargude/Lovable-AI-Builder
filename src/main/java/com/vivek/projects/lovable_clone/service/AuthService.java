package com.vivek.projects.lovable_clone.service;

import com.vivek.projects.lovable_clone.dto.auth.AuthResponse;
import com.vivek.projects.lovable_clone.dto.auth.LoginRequest;
import com.vivek.projects.lovable_clone.dto.auth.SignupRequest;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
