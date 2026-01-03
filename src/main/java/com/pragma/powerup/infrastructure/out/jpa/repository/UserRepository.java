package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findById(long userId);
    Optional<UserEntity> findByCorreo(String correo);
    void deleteById(long userId);

    @Query(
            value = "SELECT " +
                    "u.id," +
                    "u.nombre, " +
                    "u.apellido, " +
                    "u.celular, " +
                    "u.correo, " +
                    "u.dni, " +
                    "u.fecha_nacimiento, " +
                    "u.rol, " +
                    "u.password " +
                    "FROM plazoleta.jefe_empleado je " +
                    "JOIN plazoleta.user u ON u.id = je.empleado_id " +
                    "WHERE je.jefe_id = :jefeId " +
                    "ORDER BY u.apellido, u.nombre",
            nativeQuery = true
    )
    List<UserEntity> findEmpleadosPorJefe(@Param("jefeId") Long jefeId);
}

