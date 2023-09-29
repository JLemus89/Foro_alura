package com.example.ia_foro.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ia_foro.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro alura")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getUser_id())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("IA Foro")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTCreationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }
}
