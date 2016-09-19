package com.mtsmda.validation.structure.validator;

import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.helper.ReflectionHelper;
import com.mtsmda.object.request.RegistrationReqObj;
import com.mtsmda.validation.structure.constraint.PasswordEquals;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by MTSMDA on 31.08.2016.
 */
public class PasswordEqualsValidator implements ConstraintValidator<PasswordEquals, Object> {

    private String[] properties;

    @Override
    public void initialize(PasswordEquals constraintAnnotation) {
        properties = constraintAnnotation.properties();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            if (value instanceof RegistrationReqObj) {
                RegistrationReqObj registrationReqObj = (RegistrationReqObj) value;

                if (ObjectHelper.objectIsNotNull(properties) && properties.length >= 2) {
                    Object fieldPass1 = ReflectionHelper.getFieldValueInClass(registrationReqObj, properties[0]);
                    Object fieldPass2 = ReflectionHelper.getFieldValueInClass(registrationReqObj, properties[1]);
                        return fieldPass1 instanceof String && fieldPass2 instanceof String
                                && ((String)fieldPass1).equals(((String)fieldPass2));
                }

            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}