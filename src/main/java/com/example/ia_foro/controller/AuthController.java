package com.example.ia_foro.controller;

import com.example.ia_foro.infra.security.JWTTokenDTO;
import com.example.ia_foro.model.user.AuthUserDTO;
import com.example.ia_foro.model.user.User;
import com.example.ia_foro.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid AuthUserDTO authUserDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(authUserDTO.email(), authUserDTO.password());
        var userAuth = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((User) userAuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }
}
