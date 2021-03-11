package com.JanCode.SKPplus.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({
        TYPE,
        ANNOTATION_TYPE
})
@Retention(RUNTIME)
@Constraint(validatedBy = OnlyOneOfTwoValidator.class)
@Documented
public @interface OnlyOneOfTwo {
    String message() default "{constraints.only-one-of-two}";
    Class < ? > [] groups() default {};
    Class < ? extends Payload > [] payload() default {};
    String first();
    String second();

    @Target({
            TYPE,
            ANNOTATION_TYPE
    })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        OnlyOneOfTwo[] value();
    }
}