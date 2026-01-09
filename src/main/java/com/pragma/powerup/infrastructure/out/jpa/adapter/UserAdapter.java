package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.UserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserAdapter implements UserPersistencePort {

    private final UserRepository repository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        return mapper.toUser(repository.save(mapper.toEntity(user)));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(long userId) {
        return repository.findById(userId)
                .map(mapper::toUser)
                .orElseThrow(NoDataFoundException::new);


    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByCorreo(email)
                .map(mapper::toUser)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));
    }

    @Override
    public void updateUser(User user) {
        repository.save(mapper.toEntity(user));
    }

    @Override
    public void deleteUser(String userId) {
        repository.deleteById(Long.parseLong(userId));
    }
}
