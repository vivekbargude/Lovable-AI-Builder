package com.vivek.projects.lovable_clone.service;

import com.vivek.projects.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
