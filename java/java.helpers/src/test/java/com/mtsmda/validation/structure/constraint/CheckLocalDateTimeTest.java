package com.mtsmda.validation.structure.constraint;

import com.mtsmda.object.request.RegistrationReqObj;
import com.mtsmda.validation.structure.StructureValidator;
import com.mtsmda.validation.structure.sequence.FirstOrder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static com.mtsmda.validation.structure.constraint.ForTesting.*;

/**
 * Created by dminzat on 9/6/2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckLocalDateTimeTest {

    @Test
    public void checkLocalDateTimeTest1000True() {
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, FirstOrder.class);
        assertNotNull(validate);
        assertTrue(validate.getSuccessValidation());
        assertNull(validate.getConstraintViolations());
    }

    @Test
    public void checkLocalDateTimeTest1001False() {
        String userDateOfBirth = registrationReqObj.getUserDateOfBirth();
        registrationReqObj.setUserDateOfBirth("02.12.198");
        StructureValidator<RegistrationReqObj>.StructureValidationResult validate = registrationReqObjStructureValidator.validate(registrationReqObj, FirstOrder.class);
        assertNotNull(validate);
        assertFalse(validate.getSuccessValidation());
        assertNotNull(validate.getConstraintViolations());
        assertFalse(validate.getConstraintViolations().isEmpty());
        registrationReqObj.setUserDateOfBirth(userDateOfBirth);
    }

}