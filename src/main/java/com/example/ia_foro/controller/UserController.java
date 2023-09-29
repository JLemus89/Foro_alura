package com.example.ia_foro.controller;

import com.example.ia_foro.model.user.RegisterUserDTO;
import com.example.ia_foro.model.user.ResponseUserDTO;
import com.example.ia_foro.model.user.User;
import com.example.ia_foro.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseUserDTO> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO, UriComponentsBuilder uriComponentsBuilder){
        String codePassword = passwordEncoder.encode(registerUserDTO.password());

        User user = userRepository.save(new User(registerUserDTO, codePassword));
        ResponseUserDTO responseUserDTO = new ResponseUserDTO(user);
        URI url = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(user.getUser_id()).toUri();
        return ResponseEntity.created(url).body(responseUserDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseUserDTO>> getUsers(@PageableDefault(size = 10) Pageable paginacion) {
        Page<User> userPage = userRepository.findAll(paginacion);
        Page<ResponseUserDTO> responseUserDTOPage = userPage.map(ResponseUserDTO::new);
        return ResponseEntity.ok(responseUserDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUser(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO(user);
        return ResponseEntity.ok(responseUserDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @RequestBody @Valid RegisterUserDTO registerUserDTO) {
        String codePassword = passwordEncoder.encode(registerUserDTO.password());
        User user = userRepository.getReferenceById(id);
        user.updateUser(registerUserDTO, codePassword);
        return ResponseEntity.ok(new ResponseUserDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        userRepository.delete(user);
    }
}
