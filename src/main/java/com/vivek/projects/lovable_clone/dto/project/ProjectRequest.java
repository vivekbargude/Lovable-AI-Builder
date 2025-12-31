package com.vivek.projects.lovable_clone.dto.project;

import jakarta.validation.constraints.NotBlank;

public record ProjectRequest(

        @NotBlank(message = "Project name must not be empty")
        String name
) {
}
