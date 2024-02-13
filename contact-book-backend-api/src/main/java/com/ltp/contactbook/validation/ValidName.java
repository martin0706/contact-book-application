package com.ltp.contactbook.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidNameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Allow only letters (Latin and Cyrillic), space, and hyphen characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}