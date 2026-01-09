package com.pragma.powerup.application.dto;

import com.pragma.powerup.application.validation.MayorDeEdad;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {

    private long id;
    private String nombre;
    private String apellido;
    @NotBlank(message = "El documento es obligatorio")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "El documento de identidad debe contener solo números"
    )
    private String dni;
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 13, message = "El teléfono debe tener máximo 13 caracteres")
    @Pattern(
            regexp = "^\\+?[0-9]+$",
            message = "El teléfono solo puede contener números y opcionalmente el símbolo +"
    )
    private String celular;
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @MayorDeEdad
    private String fechaNacimiento;
    private String correo;
    private String password;
    private long rol;

}
