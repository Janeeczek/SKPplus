package com.JanCode.SKPplus.constraint;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyNotNullValidator implements ConstraintValidator< MyNotNull, String > {

    @Override
    public void initialize(final MyNotNull constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String s, final ConstraintValidatorContext context) {
        try {
            return s == null || s.length() > 3;
        } catch (final Exception ignore) {}
        return true;
    }
}

