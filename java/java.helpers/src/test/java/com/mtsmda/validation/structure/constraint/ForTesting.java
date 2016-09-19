package com.mtsmda.validation.structure.constraint;


import com.mtsmda.object.request.RegistrationReqObj;
import com.mtsmda.validation.structure.StructureValidator;

/**
 * Created by dminzat on 9/6/2016.
 */
public class ForTesting {

    public static RegistrationReqObj registrationReqObj = new RegistrationReqObj();
    public static StructureValidator<RegistrationReqObj> registrationReqObjStructureValidator = new StructureValidator<>();

    static {
        registrationReqObj.setAccountUsername("testUserName");
        registrationReqObj.setAccountPassword("test0#UserName");
        registrationReqObj.setAccountPassword2("test0#UserName");
        registrationReqObj.setUserFirstName("Ionuta");
        registrationReqObj.setUserLastName("Ion");
        registrationReqObj.setUserMiddleName("Ionovici");
        registrationReqObj.setUserGender("M");
        registrationReqObj.setUserDateOfBirth("02.12.1983");
        registrationReqObj.setUserEmail("ion.ionuta@mail.md");
    }

}