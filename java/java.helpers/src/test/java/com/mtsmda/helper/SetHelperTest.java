package com.mtsmda.helper;

import org.testng.annotations.Test;
import org.testng.internal.PackageUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;
import static com.mtsmda.helper.SetHelper.*;

/**
 * Created by dminzat on 10/24/2016.
 */
public class SetHelperTest {

    @Test
    public void testIsNotNullAndNotEmpty() {
        Set<String> set = null;
        assertFalse(isNotNullAndNotEmpty(set));
        set = new HashSet<>();
        assertFalse(isNotNullAndNotEmpty(set));
        set.add("");
        assertTrue(isNotNullAndNotEmpty(set));
    }

    @Test
    public void testConvertSetToStringWithDelimiter() {
        Set<String> set = createSet("Str1", "Str2", "Str3");
        String s = convertSetToStringWithDelimiter(set, null);
        assertNotNull(s);
        assertEquals(s, "Str1,Str2,Str3");
        set.removeIf(s1 -> {
            return s1.equals("Str3");
        });
        s = convertSetToStringWithDelimiter(set, "-|-|-");
        assertNotNull(s);
        assertEquals(s, "Str1-|-|-Str2");
    }

    @Test
    public void testCreateSet() {
        Set<String> set = createSet("Str1", "Str2", "Str3");
        assertNotNull(set);
        assertTrue(set.size() == 3);
        set.add("str1");
        set.add("Str1");
        assertTrue(set.size() == 4);
    }

    @Test
    public void testConvertSetToList() {
        Set<String> set = createSet("Str1", "Str2", "Str3");
        List<String> strings = convertSetToList(set);
        assertNotNull(strings);
        assertTrue(strings.size() == set.size());
    }

}