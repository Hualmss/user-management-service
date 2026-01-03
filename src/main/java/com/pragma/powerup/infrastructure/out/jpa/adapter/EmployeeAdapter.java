package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.api.EmployeeServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.EmployeePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.Entity.OwnerEmployee;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserEmployeeRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmployeeAdapter implements EmployeePersistencePort {

    private final UserRepository userRepository;
    private final UserEmployeeRepository userEmployeeRepository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveEmployeeOwnerRelation(User user, long ownerId) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User employeeSaved = mapper.toUser(userRepository.save(mapper.toEntity(user)));
        userEmployeeRepository.save(
                OwnerEmployee.builder()
                        .empleado_id(employeeSaved.getId())
                        .jefe_id(ownerId)
                        .build()
        );

    }

    @Override
    public List<User> getAllUsersByOwnerId(long propietarioId) {
        return userRepository.findEmpleadosPorJefe(propietarioId)
                .stream()
                .map(mapper::toUser)
                .collect(Collectors.toList());
    }
}
