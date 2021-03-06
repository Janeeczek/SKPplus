package com.JanCode.SKPplus.constraint;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageSizeValidator implements ConstraintValidator<ImageSize, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        byte[] bity = null;

        try {
            bity = multipartFile.getBytes();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        if(bity.length > 0 && bity != null) {
            InputStream is = new ByteArrayInputStream(bity);
            BufferedImage image = null; //ImageIO.read(is);
            try {
                image = ImageIO.read(is);;
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            System.out.println("IMAGE ROZMIAR: " + width + "x"+height);
            if (width <= 1280 && height <= 720 ) return true;
            else return false;
        } else return true;


    }
}
