package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.UserRequest;
import com.pragma.powerup.application.dto.UserResponse;

import java.util.List;

public interface EmployeeHandler {

    void saveEmployee(UserRequest user, long propietarioId);
    List<UserResponse> getAllEmployees(long propietarioId);
    Long getBossByEmployeeId (long employeeId);
}
