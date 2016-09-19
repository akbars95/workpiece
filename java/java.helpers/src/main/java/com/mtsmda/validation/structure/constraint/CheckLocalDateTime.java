package com.mtsmda.validation.structure.constraint;

import com.mtsmda.validation.structure.validator.CheckLocalDateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by dminzat on 9/1/2016.
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckLocalDateTimeValidator.class)
@Documented
public @interface CheckLocalDateTime {

    String message() default "{org.mtsmda.validator.checkLocalDateTime.message}";

    Class<?>[] groups() default { };

    DateEnum dateType();

    DateEnum datePeriod() default DateEnum.NONE;

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CheckLocalDateTime[] value();
    }

}