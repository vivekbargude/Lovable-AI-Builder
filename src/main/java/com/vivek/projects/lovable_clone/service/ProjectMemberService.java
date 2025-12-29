package com.vivek.projects.lovable_clone.service;

import com.vivek.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.vivek.projects.lovable_clone.dto.member.MemberResponse;
import com.vivek.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
