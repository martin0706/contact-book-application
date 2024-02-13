package com.ltp.contactbook.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidPinValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPin {
    String message() default "PIN allow only digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}