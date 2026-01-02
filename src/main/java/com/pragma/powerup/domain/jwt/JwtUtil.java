package com.pragma.powerup.domain.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.management.ValueExp;
import java.util.Date;
import java.util.Map;

import static com.pragma.powerup.domain.util.constans.Constants.ROL_OWNER_IDENTIFIER;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "mi-clave-super-secreta-para-jwt-256-bits-minimo";
    private final Map<Long, String> map = Map.of(1L, "ADMIN", ROL_OWNER_IDENTIFIER, "OWNER", 3L, "EMPLOYEE");

    public String generateToken(Long userId, String role, String email) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
    public String mapRole(Long role){

        System.out.println("ROLS: "+map.get(role));
        System.out.println("nukmber: "+role);
        return map.get(role);

    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
