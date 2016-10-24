package com.mtsmda.password;


import com.mtsmda.generator.password.EncoderType;
import org.testng.annotations.Test;

import static com.mtsmda.generator.password.PasswordEncoder.*;
import static org.testng.Assert.*;
/**
 * Created by dminzat on 10/22/2016.
 */
public class PasswordEncoderTest {

    @Test
    public void testEncodePasswordReverseEncoderType(){
        EncoderType reverse = EncoderType.REVERSE;
        String ivanov = "Ivanov";
        String encodedPassword = encodePassword(ivanov, reverse);
        assertNotNull(encodedPassword);
        System.out.println(encodedPassword);
        String decodePassword = decodePassword(encodedPassword, reverse);
        assertNotNull(decodePassword);
        assertEquals(ivanov, decodePassword);
        System.out.println(decodePassword);
    }

    @Test
    public void testEncodePasswordLowerCaseEncoderType(){
        EncoderType reverse = EncoderType.TO_LOWER_CASE;
        String ivanov = "Ivanov";
        String encodedPassword = encodePassword(ivanov, reverse);
        assertNotNull(encodedPassword);
        System.out.println(encodedPassword);
        String decodePassword = decodePassword(encodedPassword, reverse);
        assertNotNull(decodePassword);
        assertEquals(ivanov, decodePassword);
        System.out.println(decodePassword);
    }

}