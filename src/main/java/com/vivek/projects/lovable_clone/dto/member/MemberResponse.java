package com.vivek.projects.lovable_clone.dto.member;

import com.vivek.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String email,
        String name,
        String avatarUrl,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
