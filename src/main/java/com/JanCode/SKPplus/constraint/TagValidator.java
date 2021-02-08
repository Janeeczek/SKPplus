package com.JanCode.SKPplus.constraint;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class TagValidator implements ConstraintValidator<Tag, String> {

    @Override
    public boolean isValid(String tag, ConstraintValidatorContext constraintValidatorContext) {
        return tag.length()<6;
    }
}