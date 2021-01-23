package com.JanCode.SKPplus.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyLatinValidator implements ConstraintValidator< OnlyLatin, String > {
    @Override
    public void initialize(OnlyLatin constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return s.matches("\\w+");
        } catch (final Exception ignore) {}
        return true;
    }
}
