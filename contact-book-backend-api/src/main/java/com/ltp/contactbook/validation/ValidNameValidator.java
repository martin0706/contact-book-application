package com.ltp.contactbook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    private static final Pattern NAME_PATTERN = Pattern.compile("[\\p{L}-\\s]+");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && NAME_PATTERN.matcher(value).matches();
    }
}
