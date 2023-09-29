package com.example.ia_foro.model.user;

import jakarta.validation.constraints.Email;

public record UpdateUserDTO(
        Long user_id,
        String nombre,
        @Email
        String email,
        String password
) {
}
