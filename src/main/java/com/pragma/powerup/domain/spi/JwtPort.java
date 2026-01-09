package com.pragma.powerup.domain.spi;

public interface JwtPort {
    public String generateToken(Long userId, String role, String email);
}
