package com.ltp.contactbook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidPinSizeValidator implements ConstraintValidator<ValidPinSize, String> {

    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d+$");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }
        return value.length() == 10;
    }
}
