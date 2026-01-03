package com.vivek.projects.lovable_clone.service.impl;

import com.vivek.projects.lovable_clone.dto.project.ProjectRequest;
import com.vivek.projects.lovable_clone.dto.project.ProjectResponse;
import com.vivek.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.vivek.projects.lovable_clone.entity.Project;
import com.vivek.projects.lovable_clone.entity.ProjectMember;
import com.vivek.projects.lovable_clone.entity.ProjectMemberId;
import com.vivek.projects.lovable_clone.entity.User;
import com.vivek.projects.lovable_clone.enums.ProjectRole;
import com.vivek.projects.lovable_clone.error.ResourceNotFoundException;
import com.vivek.projects.lovable_clone.mapper.ProjectMapper;
import com.vivek.projects.lovable_clone.repository.ProjectMemberRepository;
import com.vivek.projects.lovable_clone.repository.ProjectRepository;
import com.vivek.projects.lovable_clone.repository.UserRepository;
import com.vivek.projects.lovable_clone.security.AuthUtil;
import com.vivek.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectMemberRepository projectMemberRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();


//        User owner = userRepository.findById(userId).orElseThrow(
//                ()-> new ResourceNotFoundException("User",userId.toString())
//        );


        //Eliminates a DB call.
        //Creates a demo object of user class with given userId only no other parameters.
        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        project = projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);



        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .toList();

        return projectMapper.toListOfProjectSummaryResponse(projectRepository.findAllAccessibleByUser(userId));

    }
    @Override
    @PreAuthorize("@security.canViewProject(#projectId)")
    public ProjectResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);
        return projectMapper.toProjectResponse(project);
    }



    @Override
    @PreAuthorize("@security.canEditProject(#projectId)")
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);


        //Check whether user is owner or not.
//        if(!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to delete");
//        }

        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @PreAuthorize("@security.canDeleteProject(#projectId)")
    public void softDelete(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(projectId, userId);

        //Check whether user is owner or not.
//        if(!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to delete");
//        }

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    ///  INTERNAL FUNCTION

    //Get all accessible projects by user as (owner + member).
    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId)
                .orElseThrow(()-> new ResourceNotFoundException("Project",projectId.toString()));
    }
}
