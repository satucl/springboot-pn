package com.javatechie.valiadtion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PersonalNumberValidator.class)
public @interface ValidPersonalNumber {

    public String message() default "Invalid personnummer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
