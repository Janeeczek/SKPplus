package com.JanCode.SKPplus.constraint;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyOneOfTwoValidator implements ConstraintValidator < OnlyOneOfTwo, Object > {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final OnlyOneOfTwo constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            return firstObj.equals("") && secondObj.equals("true") || firstObj != null && secondObj.equals("false") || firstObj != null && secondObj.equals("true") ;
        } catch (final Exception ignore) {}
        return true;
    }

}
