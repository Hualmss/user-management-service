package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;
import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface EmployeeServicePort {
    void saveEmployee(User user, long propietarioId);
    List<User> getAllEmployees(long propietarioId);
    long getBossByEmployeeId(long employeeId);
}
