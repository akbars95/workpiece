package com.mtsmda.helper;

import org.testng.annotations.Test;

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

}