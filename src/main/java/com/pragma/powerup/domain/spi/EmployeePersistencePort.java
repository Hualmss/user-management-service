package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface EmployeePersistencePort {

    void saveEmployeeOwnerRelation (User user, long ownerId);
    List<User> getAllUsersByOwnerId(long propietarioId);
    long getBossByEmployeeId(long employeeId);
}
