package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ClientServicePort;
import com.pragma.powerup.domain.api.EmployeeServicePort;
import com.pragma.powerup.domain.api.UserServicePort;
import com.pragma.powerup.domain.spi.ClientPersistencePort;
import com.pragma.powerup.domain.spi.EmployeePersistencePort;
import com.pragma.powerup.domain.spi.UserPersistencePort;
import com.pragma.powerup.domain.usecase.ClientCaseUse;
import com.pragma.powerup.domain.usecase.EmployeeCaseUse;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.ClientAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.EmployeeAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserEmployeeRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserRepository repository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserEmployeeRepository userEmployeeRepository;


    @Bean
    public UserPersistencePort userPersistencePort(){
        return new UserAdapter(repository, mapper, passwordEncoder);
    }

    @Bean
    public UserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public EmployeePersistencePort employeePersistencePort(){
        return new EmployeeAdapter(repository, userEmployeeRepository, mapper, passwordEncoder);
    }

    @Bean
    public EmployeeServicePort employeeServicePort(){
        return new EmployeeCaseUse(userPersistencePort() ,employeePersistencePort());
    }

    @Bean
    public ClientPersistencePort clientPersistencePort(){
        return new ClientAdapter(repository, mapper, passwordEncoder);
    }

    @Bean
    public ClientServicePort clientServicePort(){
        return new ClientCaseUse(clientPersistencePort());
    }


}

