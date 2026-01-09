package com.pragma.powerup.domain.util.constans;

import java.util.Arrays;

public enum EnumRol {

    ADMIN(1L, "ADMIN"),
    OWNER(2L, "OWNER"),
    EMPLOYEE(3L, "EMPLOYEE"),
    CLIENT(4L, "CLIENT");

    private final Long id;
    private final String name;

    EnumRol(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EnumRol fromId(Long id) {
        return Arrays.stream(values())
                .filter(r -> r.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rol inválido: " + id));
    }
}