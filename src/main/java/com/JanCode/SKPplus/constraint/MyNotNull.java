package com.JanCode.SKPplus.constraint;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({
        TYPE,
        FIELD
})
@Retention(RUNTIME)
@Constraint(validatedBy = MyNotNullValidator.class)
@Documented
public @interface MyNotNull {
    String message() default "{constraints.field-match}";
    Class < ? > [] groups() default {};
    Class < ? extends Payload > [] payload() default {};

}