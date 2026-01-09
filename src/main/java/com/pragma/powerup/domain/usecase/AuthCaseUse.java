package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.application.dto.LoginRequest;
import com.pragma.powerup.domain.api.AuthServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.CryptoUtilPort;
import com.pragma.powerup.domain.spi.JwtPort;
import com.pragma.powerup.domain.spi.UserPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;

import static com.pragma.powerup.domain.util.constans.Constants.MAP_ROLES;

public class AuthCaseUse implements AuthServicePort {

    private final UserPersistencePort userPersistencePort;
    private final JwtPort jwtPort;
    private final CryptoUtilPort cryptoUtilPort;


    public AuthCaseUse(UserPersistencePort userPersistencePort, JwtPort jwtPort, CryptoUtilPort cryptoUtilPort) {
        this.userPersistencePort = userPersistencePort;
        this.jwtPort = jwtPort;
        this.cryptoUtilPort = cryptoUtilPort;
    }


    @Override
    public String getAuthToken(LoginRequest request) {

        User user = userPersistencePort.getUserByEmail(request.getEmail());
        System.out.println(request.getPassword());
        if (!cryptoUtilPort.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }
        return jwtPort.generateToken(
                user.getId(),
                MAP_ROLES.get(user.getRol()),
                user.getCorreo()
        );
    }
}
