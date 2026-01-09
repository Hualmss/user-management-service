package com.pragma.powerup.application.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MayorDeEdadValidator implements ConstraintValidator<MayorDeEdad, String> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            LocalDate fechaNacimiento = LocalDate.parse(value, FORMATTER);
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            return edad > 18;
        } catch (Exception e) {
            return false;
        }
    }
}
