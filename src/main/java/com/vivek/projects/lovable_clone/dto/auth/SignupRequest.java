package com.vivek.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(

        @NotBlank(message = "Username (email) must not be empty")
        @Email(message = "Username must be a valid email address")
        String username,

        @NotBlank(message = "Name must not be empty")
        @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters")
        String name,

        @NotBlank(message = "Password must not be empty")
        @Size(min = 4, message = "Password must be at least 4 characters long")
        String password
) {
}
