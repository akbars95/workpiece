package com.mtsmda.password;


import com.mtsmda.generator.password.EncoderType;
import com.mtsmda.generator.password.PasswordEncoder;
import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.ObjectHelper;
import org.testng.annotations.Test;

import java.util.List;

import static com.mtsmda.generator.password.PasswordEncoder.*;
import static org.testng.Assert.*;

/**
 * Created by dminzat on 10/22/2016.
 */
public class PasswordEncoderTest {

    @Test
    public void testEncodePasswordReverseEncoderType() {
        encodePassword(EncoderType.TO_LOWER_CASE, null);
    }

    @Test
    public void testEncodePasswordLowerCaseEncoderType() {
        encodePassword(EncoderType.TO_LOWER_CASE, null);
    }

    @Test
    public void testEncodePasswordUpperCaseEncoderType() {
        encodePassword(EncoderType.TO_UPPER_CASE, null);
    }

    @Test
    public void testEncodePasswordPhoneTypeCustom() {
        encodePassword(EncoderType.PHONE_TYPE_CUSTOM, null);
    }

    @Test
    public void testEncodePasswordDefaultShift() {
        encodePassword(EncoderType.SHIFT_DEFAULT, null);
    }

    @Test
    public void testEncodePasswordDefaultRandom() {
        encodePassword(EncoderType.SHIFT_RANDOM, null);
    }

    @Test
    public void testEncodePasswordDefaultCustom() {
        encodePassword(EncoderType.SHIFT_CUSTOM, 153);
    }

    private void encodePassword(EncoderType reverse, Integer shiftNumber) {
        /*String ivanov = "I!#vanov";
        String encodedPassword = null;
        if(reverse == EncoderType.SHIFT_CUSTOM && ObjectHelper.objectIsNotNull(shiftNumber)){
            encodedPassword = PasswordEncoder.encodePassword(ivanov, reverse, shiftNumber);
        }else{
            encodedPassword = PasswordEncoder.encodePassword(ivanov, reverse);
        }
        assertNotNull(encodedPassword);
        System.out.println(encodedPassword);
        String decodePassword = null;
        if(reverse == EncoderType.SHIFT_CUSTOM && ObjectHelper.objectIsNotNull(shiftNumber)){
            decodePassword = PasswordEncoder.decodePassword(encodedPassword, reverse, shiftNumber);
        }else{
            decodePassword = PasswordEncoder.decodePassword(encodedPassword, reverse);
        }
        assertNotNull(decodePassword);
        assertEquals(ivanov, decodePassword);
        System.out.println(decodePassword);*/
        List<String> passwords = ListHelper.getListWithData("IvaNoVic190", "5345$%$iojikNasH", "this is passwordSesE");
        passwords.forEach(current -> {
            String encodePassword = null;
            if(reverse == EncoderType.SHIFT_CUSTOM && ObjectHelper.objectIsNotNull(shiftNumber)){
                encodePassword = PasswordEncoder.encodePassword(current, reverse, shiftNumber);
            }else{
                encodePassword = PasswordEncoder.encodePassword(current, reverse);
            }
            assertNotNull(encodePassword);
            System.out.println(encodePassword);
            String decodePassword1 = null;
            if(reverse == EncoderType.SHIFT_CUSTOM && ObjectHelper.objectIsNotNull(shiftNumber)){
                decodePassword1 = decodePassword(encodePassword, reverse, shiftNumber);
            }else{
                decodePassword1 = decodePassword(encodePassword, reverse);
            }
            assertNotNull(decodePassword1);
            assertEquals(decodePassword1, current);
        });
    }

}