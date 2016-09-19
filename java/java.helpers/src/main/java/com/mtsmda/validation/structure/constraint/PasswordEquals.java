package com.mtsmda.validation.structure.constraint;

import com.mtsmda.validation.structure.validator.PasswordEqualsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by MTSMDA on 31.08.2016.
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordEqualsValidator.class)
@Documented
public @interface PasswordEquals {

    String message() default "{org.mtsmda.validator.passwordEquals.message}";

    Class<?>[] groups() default { };

    Class<?> className() ;

    String [] properties() default {};

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PasswordEquals[] value();
    }

}