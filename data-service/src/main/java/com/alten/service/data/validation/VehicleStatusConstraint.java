package com.alten.service.data.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VehicleStatusConstraintValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface VehicleStatusConstraint {

    public String message() default "Allowable values for vehicle status are Connected and Disconnected!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}