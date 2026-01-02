package com.pragma.powerup.infrastructure.out.jpa.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String celular;
    private String fechaNacimiento;
    private String correo;
    private String password;
    private long rol;
}
