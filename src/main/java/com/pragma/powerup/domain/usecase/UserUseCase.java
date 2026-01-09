package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.EmployeeServicePort;
import com.pragma.powerup.domain.api.UserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.CryptoUtilPort;
import com.pragma.powerup.domain.spi.UserPersistencePort;

import java.util.List;

import static com.pragma.powerup.domain.util.constans.Constants.ROL_OWNER_IDENTIFIER;

public class UserUseCase implements UserServicePort {

    private final CryptoUtilPort cryptoUtilPort;
    private final UserPersistencePort userPersistencePort;

    public UserUseCase(CryptoUtilPort cryptoUtilPort, UserPersistencePort userPersistencePort) {
        this.cryptoUtilPort = cryptoUtilPort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User saveUser(User user) {
        user.setRol(ROL_OWNER_IDENTIFIER);
        user.setPassword(cryptoUtilPort.encryptPassword(user.getPassword()));
        return userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public User getUser(long userId) {
        return userPersistencePort.getUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        userPersistencePort.deleteUser(userId);
    }
}
