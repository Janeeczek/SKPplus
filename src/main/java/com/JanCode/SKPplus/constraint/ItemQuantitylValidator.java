package com.JanCode.SKPplus.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ItemQuantitylValidator implements ConstraintValidator< ItemQuantity, Integer> {


    @Override
    public boolean isValid(final Integer i, final ConstraintValidatorContext context) {
        return i>0;
    }
}

