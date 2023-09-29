package com.example.ia_foro.infra.security;

import com.example.ia_foro.repository.UserRepository;
import com.example.ia_foro.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var userName = tokenService.getSubject(token);
            if (userName != null) {
                var user = userRepository.findByUsername(userName);
                var authentication = new UsernamePasswordAuthenticationToken(userName, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
