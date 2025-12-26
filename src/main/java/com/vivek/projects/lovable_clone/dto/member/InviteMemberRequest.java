package com.vivek.projects.lovable_clone.dto.member;

import com.vivek.projects.lovable_clone.enums.ProjectRole;
import com.vivek.projects.lovable_clone.service.ProjectMemberService;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
