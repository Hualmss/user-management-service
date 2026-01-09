package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.LoginRequest;
import com.pragma.powerup.domain.api.AuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthHandlerImpl implements AuthHandler{
    private final AuthServicePort authServicePort;
    @Override
    public String getAuthToken(LoginRequest request) {
        return authServicePort.getAuthToken(request);
    }
}
