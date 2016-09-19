package com.mtsmda.validation.structure.constraint;

import com.mtsmda.validation.structure.validator.IsNotNullValidator;

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
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = IsNotNullValidator.class)
@Documented
public @interface IsNotNull {

    String message() default "{org.mtsmda.validator.isNotNull.message}";

    Class<?>[] groups() default { };

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        IsNotNull[] value();
    }

}