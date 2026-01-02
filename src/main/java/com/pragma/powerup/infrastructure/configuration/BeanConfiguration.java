package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.UserServicePort;
import com.pragma.powerup.domain.spi.UserPersistencePort;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
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


    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserAdapter(repository, mapper, passwordEncoder);
    }

    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }


}

