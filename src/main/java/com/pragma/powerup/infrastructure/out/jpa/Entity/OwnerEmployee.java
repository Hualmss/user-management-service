package com.pragma.powerup.infrastructure.out.jpa.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "jefe_empleado")
public class OwnerEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "jefe_id")
    private long jefeId;
    @Column(name = "empleado_id")
    private long empleadoId;
}
