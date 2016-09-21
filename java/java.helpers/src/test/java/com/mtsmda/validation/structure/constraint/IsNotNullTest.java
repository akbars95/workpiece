package com.mtsmda.validation.structure.constraint;

import com.mtsmda.object.request.RegistrationReqObj;
import com.mtsmda.validation.structure.StructureValidator;
import com.mtsmda.validation.structure.sequence.FifthOrder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.validation.groups.Default;

import static com.mtsmda.validation.structure.constraint.ForTesting.*;
import static org.junit.Assert.*;

/**
 * Created by dminzat on 9/6/2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IsNotNullTest {

    @Test
    public void isNotNullTest1000True() {
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, Default.class);
        assertNotNull(validate);
        assertTrue(validate.getSuccessValidation());
        assertNull(validate.getConstraintViolations());
    }

    @Test
    public void isNotNullTest1001False() {
        String userMiddleName = registrationReqObj.getUserMiddleName();
        registrationReqObj.setUserMiddleName("");
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, Default.class);
        assertNotNull(validate);
        assertFalse(validate.getSuccessValidation());
        assertFalse(validate.getConstraintViolations().isEmpty());
        assertFalse(validate.getConstraintViolations().size() == 0);
        assertTrue(validate.getConstraintViolations().size() == 1);
        registrationReqObj.setUserMiddleName(userMiddleName);
    }

    @Test
    public void isNotNullTest1002True() {
        String userMiddleName = registrationReqObj.getUserMiddleName();
        registrationReqObj.setUserMiddleName(null);
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, FifthOrder.class);
        assertNotNull(validate);
        assertTrue(validate.getSuccessValidation());
        assertNull(validate.getConstraintViolations());
        registrationReqObj.setUserMiddleName(userMiddleName);
    }

}