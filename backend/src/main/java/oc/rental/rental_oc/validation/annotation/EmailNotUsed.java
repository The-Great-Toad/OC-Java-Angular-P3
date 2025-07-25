package oc.rental.rental_oc.validation.annotation;

import jakarta.validation.Constraint;
import oc.rental.rental_oc.constant.ValidationMessages;
import oc.rental.rental_oc.validation.validator.EmailNotUsedValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailNotUsedValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotUsed {
    String message() default ValidationMessages.EMAIL_ALREADY_USED;

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
