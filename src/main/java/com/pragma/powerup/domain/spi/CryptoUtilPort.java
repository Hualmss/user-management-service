package com.pragma.powerup.domain.spi;

public interface CryptoUtilPort {
    String encryptPassword(String password);
    boolean matches( CharSequence rawPassword,
                     String encodedPassword);
}
