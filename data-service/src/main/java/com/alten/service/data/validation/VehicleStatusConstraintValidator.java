package com.alten.service.data.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VehicleStatusConstraintValidator implements ConstraintValidator<VehicleStatusConstraint, String> {

    private static final String CONNECTED = "Connected";
    private static final String DISCONNECTED = "Disconnected";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        return CONNECTED.equals(value) || DISCONNECTED.equals(value);
    }
}
