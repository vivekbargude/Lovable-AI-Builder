package com.vivek.projects.lovable_clone.service.impl;

import com.vivek.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.vivek.projects.lovable_clone.dto.member.MemberResponse;
import com.vivek.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.vivek.projects.lovable_clone.entity.Project;
import com.vivek.projects.lovable_clone.entity.ProjectMember;
import com.vivek.projects.lovable_clone.entity.ProjectMemberId;
import com.vivek.projects.lovable_clone.entity.User;
import com.vivek.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.vivek.projects.lovable_clone.repository.ProjectMemberRepository;
import com.vivek.projects.lovable_clone.repository.ProjectRepository;
import com.vivek.projects.lovable_clone.repository.UserRepository;
import com.vivek.projects.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        return projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMemberMapper::toProjectMemberResponseFromMember)
                        .toList();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
//        if(!project.getOwner().getId().equals(userId)){
//            throw new RuntimeException("Action not allowed");
//        }

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Can't invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Can't invite once again.");
        }

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        member = projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
//        if(!project.getOwner().getId().equals(userId)){
//            throw new RuntimeException("Action not allowed");
//        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMember = projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
//        if(!project.getOwner().getId().equals(userId)){
//            throw new RuntimeException("Action not allowed");
//        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Member doesn't exists.");
        }

        projectMemberRepository.deleteById(projectMemberId);

    }


    ///  INTERNAL FUNCTION

    //Get all accessible projects by user as (owner + member).
    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }

}
