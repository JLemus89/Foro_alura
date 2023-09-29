package com.example.ia_foro.model.user;

public record UserDTO(
        Long user_id,
        String nombre,
        String email,
        String password
) {
    public UserDTO(User user) {
        this(
                user.getUser_id(),
                user.getNombre(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
