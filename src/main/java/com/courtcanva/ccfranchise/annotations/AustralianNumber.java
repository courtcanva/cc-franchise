package com.courtcanva.ccfranchise.annotations;

import com.courtcanva.ccfranchise.validators.AustralianNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = AustralianNumberValidator.class)
public @interface AustralianNumber {

    String message() default "This doesn't seems like a valid Australian number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
