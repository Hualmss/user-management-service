package com.pragma.powerup.infrastructure.in;

import com.pragma.powerup.application.dto.LoginRequest;
import com.pragma.powerup.domain.jwt.JwtUtil;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.Entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityMapper mapper;
    @Autowired
    private final JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByCorreo(request.getEmail())
                .map(mapper::toUser)
                .map(element -> {
                    System.out.println("elemento rol usuario "+ element.getRol() );
                    return element;
                })
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateToken(
                user.getId(),
                jwtUtil.mapRole(user.getRol()),
                user.getCorreo()
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
