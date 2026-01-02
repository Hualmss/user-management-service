package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface UserHandler {
    void saveUser(UserRequest user);
    List<UserResponse> getAllUsers();
    UserResponse getUser(long userId);
    void updateUser(UserRequest user);
    void deleteUser(String userId);
}
