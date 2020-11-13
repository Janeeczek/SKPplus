package com.JanCode.SKPplus.constraint;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyNotNullValidator implements ConstraintValidator< MyNotNull, Object > {

    @Override
    public void initialize(final MyNotNull constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            return value == null || String.valueOf(value).length() < 4;
        } catch (final Exception ignore) {}
        return true;
    }
}

