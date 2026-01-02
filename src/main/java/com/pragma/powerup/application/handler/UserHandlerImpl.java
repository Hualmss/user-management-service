package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.application.mapper.UserMapper;
import com.pragma.powerup.domain.api.UserServicePort;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandlerImpl implements UserHandler{

    private final UserServicePort userServiceport;
    private final UserMapper userMapper;

    @Override
    public void saveUser(UserRequest userRequest) {
        userServiceport.saveUser(userMapper
                .toUser(userRequest));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userServiceport.getAllUsers()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

    }

    @Override
    public UserResponse getUser(long userId) {
        return userMapper.toUserResponse(userServiceport.getUser(userId));
    }

    @Override
    public void updateUser(UserRequest userRequest) {
        userServiceport.updateUser(userMapper.toUser(userRequest));
    }

    @Override
    public void deleteUser(String userId) {
        userServiceport.deleteUser(userId);
    }
}
