package com.JanCode.SKPplus.constraint;
import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ImageSizeValidator.class)
@Documented
public @interface ImageSize {
    String message() default "{constraints.image-size}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
