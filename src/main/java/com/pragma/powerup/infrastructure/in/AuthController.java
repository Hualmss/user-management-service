package com.pragma.powerup.infrastructure.in;

import com.pragma.powerup.application.dto.LoginRequest;
import com.pragma.powerup.application.handler.AuthHandler;
import com.pragma.powerup.infrastructure.jwt.JwtUtil;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = authHandler.getAuthToken(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
