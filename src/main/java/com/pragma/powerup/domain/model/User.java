package com.pragma.powerup.domain.model;

import lombok.Data;

@Data
public class User {

    private long id;
    private String nombre;
    private  String apellido;
    private String dni;
    private String celular;
    private String fechaNacimiento;
    private String correo;
    private String password;
    private long rol;




}
