package com.ltp.contactbook.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPinSizeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPinSize {
    String message() default "PIN should contains 10 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}