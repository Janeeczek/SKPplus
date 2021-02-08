package com.JanCode.SKPplus.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
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
@Constraint(validatedBy = ItemQuantitylValidator.class)
@Documented
public @interface ItemQuantity {
    String message() default "{constraints.item-quantity}";
    Class < ? > [] groups() default {};
    Class < ? extends Payload > [] payload() default {};

}