package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.LoginRequest;
import com.pragma.powerup.domain.api.AuthServicePort;

public interface AuthHandler {

    String getAuthToken(LoginRequest request);
}
