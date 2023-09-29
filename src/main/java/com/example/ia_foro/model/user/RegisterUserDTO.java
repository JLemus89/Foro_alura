package com.example.ia_foro.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserDTO(
        Long user_id,
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
