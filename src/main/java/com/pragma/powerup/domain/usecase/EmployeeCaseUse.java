package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.EmployeeServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.CryptoUtilPort;
import com.pragma.powerup.domain.spi.EmployeePersistencePort;
import com.pragma.powerup.domain.spi.UserPersistencePort;

import java.util.List;

import static com.pragma.powerup.domain.util.constans.Constants.ROL_EMPLOYEE_IDENTIFIER;
import static com.pragma.powerup.domain.util.constans.Constants.ROL_OWNER_IDENTIFIER;

public class EmployeeCaseUse implements EmployeeServicePort {

    private final UserPersistencePort userPersistencePort;
    private final EmployeePersistencePort employeePersistencePort;
    private final CryptoUtilPort cryptoUtilPort;

    public EmployeeCaseUse(UserPersistencePort userPersistencePort, EmployeePersistencePort employeePersistencePort, CryptoUtilPort cryptoUtilPort) {
        this.userPersistencePort = userPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.cryptoUtilPort = cryptoUtilPort;
    }


    @Override
    public void saveEmployee(User user, long propietarioId) {
        user.setRol(ROL_EMPLOYEE_IDENTIFIER);
        user.setPassword(cryptoUtilPort.encryptPassword(user.getPassword()));
        User userSaved = userPersistencePort.saveUser(user);
        employeePersistencePort.saveEmployeeOwnerRelation(
                userSaved,
                propietarioId
        );
    }

    @Override
    public List<User> getAllEmployees(long propietarioId) {
        return employeePersistencePort.getAllUsersByOwnerId(propietarioId);
    }

    @Override
    public long getBossByEmployeeId(long employeeId) {
        return employeePersistencePort.getBossByEmployeeId(employeeId);
    }
}
