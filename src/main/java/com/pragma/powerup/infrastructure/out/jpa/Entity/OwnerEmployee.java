package com.pragma.powerup.infrastructure.out.jpa.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter

@Builder
@Getter
@Table(name = "jefe_empleado")
public class OwnerEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long jefe_id;
    private long empleado_id;
}
