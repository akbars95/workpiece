package com.mtsmda.password;


import com.mtsmda.generator.password.EncoderType;
import com.mtsmda.helper.ListHelper;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.mtsmda.generator.password.PasswordEncoder.*;
import static org.testng.Assert.*;

/**
 * Created by dminzat on 10/22/2016.
 */
public class PasswordEncoderTest {

    @Test
    public void testEncodePasswordReverseEncoderType() {
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
    public void testEncodePasswordLowerCaseEncoderType() {
        EncoderType reverse = EncoderType.TO_LOWER_CASE;
        String ivanov = "Ivanov";
        String encodedPassword = encodePassword(ivanov, reverse);
        assertNotNull(encodedPassword);
        System.out.println(encodedPassword);
        String decodePassword = decodePassword(encodedPassword, reverse);
        assertNotNull(decodePassword);
        assertEquals(ivanov, decodePassword);
        System.out.println(decodePassword);
        encodePasswordLowerUpperCase(reverse);
    }

    @Test
    public void testEncodePasswordUpperCaseEncoderType() {
        EncoderType reverse = EncoderType.TO_UPPER_CASE;
        String ivanov = "Ivanov";
        String encodedPassword = encodePassword(ivanov, reverse);
        assertNotNull(encodedPassword);
        System.out.println(encodedPassword);
        String decodePassword = decodePassword(encodedPassword, reverse);
        assertNotNull(decodePassword);
        assertEquals(ivanov, decodePassword);
        System.out.println(decodePassword);
        encodePasswordLowerUpperCase(reverse);
    }

    private void encodePasswordLowerUpperCase(EncoderType reverse) {
        List<String> passwords = ListHelper.getListWithData("IvaNoVic190", "5345$%$iojikNasH", "this is passwordSesE");
        passwords.forEach(current -> {
            String encodePassword = encodePassword(current, reverse);
            assertNotNull(encodePassword);
            String decodePassword1 = decodePassword(encodePassword, reverse);
            assertNotNull(decodePassword1);
            assertEquals(decodePassword1, current);
        });
    }

}