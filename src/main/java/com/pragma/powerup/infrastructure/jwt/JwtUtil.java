package com.pragma.powerup.infrastructure.jwt;

import com.pragma.powerup.domain.spi.JwtPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.pragma.powerup.domain.util.constans.Constants.*;

@Component
public class JwtUtil implements JwtPort {

    private final String SECRET_KEY = "mi-clave-super-secreta-para-jwt-256-bits-minimo";

    public String generateToken(Long userId, String role, String email) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .claim("email", email)
                //.claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    public String mapRole(Long role){
        return MAP_ROLES.get(role);

    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
