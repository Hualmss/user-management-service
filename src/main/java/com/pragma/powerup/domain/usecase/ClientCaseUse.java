package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ClientServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.ClientPersistencePort;

import static com.pragma.powerup.domain.util.constans.Constants.ROL_CLIENT_IDENTIFIER;

public class ClientCaseUse implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort;

    public ClientCaseUse(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public void saveClient(User user) {
        user.setRol(ROL_CLIENT_IDENTIFIER);
        clientPersistencePort.saveClient(user);
    }
}
