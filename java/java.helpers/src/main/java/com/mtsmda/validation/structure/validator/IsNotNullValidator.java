package com.mtsmda.validation.structure.validator;

import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.validation.structure.constraint.IsNotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by MTSMDA on 31.08.2016.
 */
public class IsNotNullValidator implements ConstraintValidator<IsNotNull, String> {

    private int min;
    private int max;

    @Override
    public void initialize(IsNotNull constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (ObjectHelper.objectIsNull(value)) {
            return true;
        }
        return ObjectHelper.objectIsNotNull(value) && value.length() >= min && value.length() <= max;
    }
}