package com.vivek.projects.lovable_clone.dto.member;

import com.vivek.projects.lovable_clone.enums.ProjectRole;
import com.vivek.projects.lovable_clone.service.ProjectMemberService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @NotBlank @Email String username,
        @NotNull ProjectRole role
) {
}
