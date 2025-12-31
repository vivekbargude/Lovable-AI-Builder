package com.vivek.projects.lovable_clone.dto.member;

import com.vivek.projects.lovable_clone.enums.ProjectRole;
import com.vivek.projects.lovable_clone.service.ProjectMemberService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(

        @NotBlank(message = "Member email must not be empty")
        @Email(message = "Member email must be a valid email address")
        String username,

        @NotNull(message = "Project role must be provided")
        ProjectRole role
) {
}
