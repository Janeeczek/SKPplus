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
            //^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,15}$ dodaj nowy
        } catch (final Exception ignore) {}
        return true;
    }
}
