package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.ClientPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class ClientAdapter implements ClientPersistencePort {

    private final UserRepository repository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveClient(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(mapper.toEntity(user));
    }
}
