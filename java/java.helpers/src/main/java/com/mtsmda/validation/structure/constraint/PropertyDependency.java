package com.mtsmda.validation.structure.constraint;

import com.mtsmda.validation.structure.validator.PropertyDependencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by dminzat on 9/2/2016.
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PropertyDependencyValidator.class)
@Documented
public @interface PropertyDependency {

    String message() default "{org.mtsmda.validator.propertyDependency.message}";

    Class<?>[] groups() default { };

    String mainProperty();

    String subProperty();

    Class<? extends Payload>[] payload() default { };

    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PropertyDependency[] value();
    }

}