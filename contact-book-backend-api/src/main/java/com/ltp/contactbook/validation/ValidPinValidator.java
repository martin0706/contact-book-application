package com.ltp.contactbook.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidPinValidator implements ConstraintValidator<ValidPin, String> {

    private static final Pattern PIN_PATTERN = Pattern.compile("^\\d+$");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }
        return PIN_PATTERN.matcher(value).matches();
    }
}
