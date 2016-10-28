package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.mtsmda.helper.StringHelper.*;
import static org.testng.Assert.*;

/**
 * Created by MTSMDA on 25.10.2016.
 */
public class StringHelperTest {

    @Test
    public void testReplaceByIndex() {
        String source = "This is source";
        String ee = replaceByIndex(source, 5, "Name");
        assertNotNull(ee);
        assertEquals(ee, "This Names source");

        source = "Hello my friend, how do you do?";
        ee = replaceByIndex(source, 9, "ThisIsGood");
        assertNotNull(ee);
        assertEquals(ee, "Hello my ThisIsGoodriend, how do you do?");
    }

    @Test
    public void testAddLedZero() {
        List<String> testData = ListHelper.getListWithData("012", "1", "953", "12345");
        testData.forEach(current -> {
            String addLedZero = addLedZero(current, 9);
            System.out.println(addLedZero);
            assertNotNull(addLedZero);
            assertFalse(addLedZero.isEmpty());
            assertTrue(addLedZero.length() == 9);
        });

        testData = ListHelper.getListWithData(null, "", "  ");
        testData.forEach(current -> {
            try {
                addLedZero(current, 5);
            } catch (RuntimeException e) {
                assertNotNull(e.getMessage());
                assertEquals("Input text cannot be blank!", e.getMessage());
            }
        });

        String dfd = addLedZero("70356", 5);
        assertNotNull(dfd);
        assertEquals(dfd, "70356");

        String online = "12345";
        String addLedZero = addLedZero(online, 1);
        assertNotNull(addLedZero);
        assertEquals(online, addLedZero);

        testData = ListHelper.getListWithData("sdsa", "sd");
        testData.forEach(current -> {
            try {
                addLedZero(current, 5);
            } catch (RuntimeException e) {
                assertNotNull(e.getMessage());
                assertEquals("number format exception - " + current, e.getMessage());
            }
        });
    }

    @Test
    public void testDeleteLedZero() {
        Map<String, String> testData = new LinkedHashMap<>();
        testData.put("0000010002", "10002");
        testData.put("00001", "1");
        testData.put("900053", "900053");
        testData.put("00000123004500", "123004500");
        testData.put("0000900053", "900053");
        testData.forEach((key, value) -> {
            String s = deleteLedZero(key);
            System.out.println(s);
            assertNotNull(s);
            assertEquals(s, value);
        });
    }

}