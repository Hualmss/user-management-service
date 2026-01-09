package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.LoginRequest;

public interface AuthServicePort {
    String getAuthToken(LoginRequest request);
}
