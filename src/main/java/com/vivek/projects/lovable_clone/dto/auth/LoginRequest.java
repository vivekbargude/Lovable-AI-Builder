package com.vivek.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "Username (email) must not be empty")
        @Email(message = "Username must be a valid email address")
        String username,

        @NotBlank(message = "Password must not be empty")
        @Size(min = 4, max = 50, message = "Password must be between 4 and 50 characters")
        String password
) {
}
