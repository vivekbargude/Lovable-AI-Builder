package com.vivek.projects.lovable_clone.dto.member;

import com.vivek.projects.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(

        @NotNull(message = "Project role must be provided")
        ProjectRole role
) {
}
