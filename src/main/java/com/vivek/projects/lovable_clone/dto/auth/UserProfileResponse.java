package com.vivek.projects.lovable_clone.dto.auth;

public record UserProfileResponse(
        Long id,
        String email,
        String username
) {
}
