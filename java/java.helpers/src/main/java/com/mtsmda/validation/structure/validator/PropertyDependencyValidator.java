package com.mtsmda.validation.structure.validator;

import com.mtsmda.validation.structure.constraint.PropertyDependency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by dminzat on 9/2/2016.
 */
public class PropertyDependencyValidator implements ConstraintValidator<PropertyDependency, Object> {


    @Override
    public void initialize(PropertyDependency constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}