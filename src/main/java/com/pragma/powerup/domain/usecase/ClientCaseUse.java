package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ClientServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.ClientPersistencePort;
import com.pragma.powerup.domain.spi.CryptoUtilPort;

import static com.pragma.powerup.domain.util.constans.Constants.ROL_CLIENT_IDENTIFIER;

public class ClientCaseUse implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort;//reutilziar userport
    private final CryptoUtilPort cryptoUtilPort;

    public ClientCaseUse(ClientPersistencePort clientPersistencePort, CryptoUtilPort cryptoUtilPort) {
        this.clientPersistencePort = clientPersistencePort;
        this.cryptoUtilPort = cryptoUtilPort;
    }

    @Override
    public void saveClient(User user) {
        user.setRol(ROL_CLIENT_IDENTIFIER);
        user.setPassword(cryptoUtilPort.encryptPassword(user.getPassword()));
        clientPersistencePort.saveClient(user);
    }
}
