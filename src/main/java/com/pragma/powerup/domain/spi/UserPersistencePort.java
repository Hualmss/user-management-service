package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface UserPersistencePort {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(long userId);
    User getUserByEmail(String email);
    void updateUser(User user);
    void deleteUser(String userId);
}
