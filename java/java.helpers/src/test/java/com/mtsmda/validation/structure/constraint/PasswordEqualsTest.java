package com.mtsmda.validation.structure.constraint;

import com.mtsmda.object.request.RegistrationReqObj;
import com.mtsmda.validation.structure.StructureValidator;
import com.mtsmda.validation.structure.sequence.FourthOrder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.mtsmda.validation.structure.constraint.ForTesting.registrationReqObj;
import static com.mtsmda.validation.structure.constraint.ForTesting.registrationReqObjStructureValidator;
import static org.junit.Assert.*;

/**
 * Created by dminzat on 9/6/2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasswordEqualsTest {

    @Test
    public void passwordEqualsTest1000True() {
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, FourthOrder.class);
        assertNotNull(validate);
        assertTrue(validate.getSuccessValidation());
        assertNull(validate.getConstraintViolations());
    }

    @Test
    public void passwordEqualsTest1001False() {
        String accountPassword = registrationReqObj.getAccountPassword();
        registrationReqObj.setAccountPassword(registrationReqObj.getAccountPassword() + ",");
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, FourthOrder.class);
        assertNotNull(validate);
        assertFalse(validate.getSuccessValidation());
        assertNotNull(validate.getConstraintViolations());
        assertFalse(validate.getConstraintViolations().isEmpty());
        registrationReqObj.setAccountPassword(accountPassword);
    }

}