package com.example.authenticator.service;

import com.example.authenticator.repository.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SecurityService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JwtService jwtService;

    public boolean hasPermission(HttpServletRequest request, Integer userId) {
        List<SimpleGrantedAuthority> roles = jwtService.extractRoles(request.getHeader(HttpHeaders.AUTHORIZATION).substring(7));
        if(Objects.equals(usersRepository.findAllByEmail(request.getUserPrincipal().getName()).get(0).getUserId(), userId) || roles.toString().contains("ADMIN")) {
            return true;
        }
        else {
            return false;
        }

    }
}