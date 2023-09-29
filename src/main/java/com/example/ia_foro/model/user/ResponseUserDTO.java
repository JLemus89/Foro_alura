package com.example.ia_foro.model.user;

public record ResponseUserDTO(
        Long user_id,
        String nombre,
        String email
) {
    public ResponseUserDTO(User user) {
        this(
                user.getUser_id(),
                user.getNombre(),
                user.getEmail()
        );
    }
}
