package com.pragma.powerup.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String nombre;
    private  String apellido;
    private String dni;
    private String celular;
    private String fechaNacimiento;
    private String correo;


}
