package com.JanCode.SKPplus.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ImageTypeValidator.class)
@Documented
public @interface ImageType {
    String message() default "{constraints.image-type}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
