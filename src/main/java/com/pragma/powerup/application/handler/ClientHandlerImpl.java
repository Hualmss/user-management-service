package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.mapper.UserMapper;
import com.pragma.powerup.domain.api.ClientServicePort;
import com.pragma.powerup.domain.api.EmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientHandlerImpl implements  ClientHandler{

    private final ClientServicePort clientServicePort;
    private final UserMapper userMapper;

    @Override
    public void saveClient(UserRequest userRequest) {
          clientServicePort.saveClient(userMapper.toUser(userRequest));
    }
}
