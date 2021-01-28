package com.JanCode.SKPplus.constraint;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ImageTypeValidator implements ConstraintValidator<ImageType, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        String format = multipartFile.getContentType().split("/")[1];
        return format.toLowerCase().equals("jpg") || format.toLowerCase().equals("jpeg") || format.toLowerCase().equals("png");






    }
}