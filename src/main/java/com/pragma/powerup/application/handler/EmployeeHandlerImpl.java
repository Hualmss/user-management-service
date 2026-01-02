package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.application.mapper.UserMapper;
import com.pragma.powerup.domain.api.EmployeeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeHandlerImpl implements EmployeeHandler {

    private final EmployeeServicePort employeeServicePort;
    private final UserMapper userMapper;

    @Override
    public void saveEmployee(UserRequest userRequest, long propietarioId) {
        employeeServicePort.saveEmployee(userMapper
                .toUser(userRequest), propietarioId);
    }

    @Override
    public List<UserResponse> getAllEmployees(long propietarioId) {
        return employeeServicePort.getAllEmployees(propietarioId)
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

    }
}
