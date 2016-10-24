package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;
import static com.mtsmda.helper.SetHelper.*;
/**
 * Created by dminzat on 10/24/2016.
 */
public class SetHelperTest {

    @Test
    public void testIsNotNullAndNotEmpty(){
        Set<String> set = null;
        assertFalse(isNotNullAndNotEmpty(set));
        set = new HashSet<>();
        assertFalse(isNotNullAndNotEmpty(set));
        set.add("");
        assertTrue(isNotNullAndNotEmpty(set));
    }

    @Test
    public void testConvertSetToStringWithDelimiter(){
//        Set<String> strings =
    }

    @Test
    public void testCreateSet(){
        Set<String> set = createSet("Str1", "Str2", "Str3");
        assertNotNull(set);
        assertTrue(set.size() == 3);
    }

}