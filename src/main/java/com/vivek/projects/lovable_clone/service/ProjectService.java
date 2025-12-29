package com.vivek.projects.lovable_clone.service;

import com.vivek.projects.lovable_clone.dto.project.ProjectRequest;
import com.vivek.projects.lovable_clone.dto.project.ProjectResponse;
import com.vivek.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {

    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest requestd);

    void softDelete(Long id);
}
