package com.example.demobackend.common.validation;

import com.example.demobackend.common.annotation.IsUuid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UuidValidator implements ConstraintValidator<IsUuid, String> {

    private final String regex =
            "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";

    @Override
    public void initialize(IsUuid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.toLowerCase().matches(this.regex);
    }
}
