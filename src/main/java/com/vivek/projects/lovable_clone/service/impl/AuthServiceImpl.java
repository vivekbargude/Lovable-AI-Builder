package com.vivek.projects.lovable_clone.service.impl;

import com.vivek.projects.lovable_clone.dto.auth.AuthResponse;
import com.vivek.projects.lovable_clone.dto.auth.LoginRequest;
import com.vivek.projects.lovable_clone.dto.auth.SignupRequest;
import com.vivek.projects.lovable_clone.entity.User;
import com.vivek.projects.lovable_clone.error.BadRequestException;
import com.vivek.projects.lovable_clone.mapper.UserMapper;
import com.vivek.projects.lovable_clone.repository.UserRepository;
import com.vivek.projects.lovable_clone.security.AuthUtil;
import com.vivek.projects.lovable_clone.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    AuthUtil authUtil;
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {

        log.info("Signup initiated for username: {}", request.username());

        userRepository.findByUsername(request.username()).ifPresent(user -> {
            log.warn("Signup failed - user already exists with username: {}", request.username());
            throw new BadRequestException("User already exists with username " + request.username());
        });

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        log.debug("User entity created for username: {}", request.username());

        user = userRepository.save(user);
        log.info("User registered successfully with userId: {}", user.getId());

        String token = authUtil.generateAccessToken(user);
        log.debug("Access token generated for userId: {}", user.getId());

        log.info("Signup completed successfully for userId: {}", user.getId());

        return new AuthResponse(
                token,
                userMapper.toUserProfileResponse(user)
        );
    }


    @Override
    public AuthResponse login(LoginRequest request) {

        log.info("Login attempt for username: {}", request.username());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = (User) authentication.getPrincipal();

        log.info("Login successful for userId: {}", user.getId());

        String token = authUtil.generateAccessToken(user);
        log.debug("Access token generated for userId: {}", user.getId());

        return new AuthResponse(
                token,
                userMapper.toUserProfileResponse(user)
        );
    }

}
