package com.example.ia_foro.model.user;

import com.example.ia_foro.model.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String nombre;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Topic> topics = new ArrayList<>();

    public User(RegisterUserDTO registerUserDTO, String codePassword) {
        this.nombre = registerUserDTO.nombre();
        this.email = registerUserDTO.password();
        this.password = codePassword;
    }

    public void updateUser(RegisterUserDTO registerUserDTO, String codePassword) {
        if (registerUserDTO.nombre() != null) {
            this.nombre = registerUserDTO.nombre();
        }
        if (registerUserDTO.email() != null) {
            this.email = registerUserDTO.email();
        }
        if (registerUserDTO.password() != null) {
            this.password = codePassword;
        }
    }
}
