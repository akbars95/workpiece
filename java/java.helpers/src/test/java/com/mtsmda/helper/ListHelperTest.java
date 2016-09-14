package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static com.mtsmda.helper.ListHelper.*;
/**
 * Created by dminzat on 9/2/2016.
 */
public class ListHelperTest {

    @Test
    public void listIsNotNullAndNotEmptyTest() {
        List<String> strings = null;
        assertFalse(listIsNotNullAndNotEmpty(strings));
        strings = new ArrayList<>();
        assertFalse(listIsNotNullAndNotEmpty(strings));
        strings.add("");
        assertTrue(listIsNotNullAndNotEmpty(strings));
    }

    @Test
    public void listAndFirstElementIsNotNullTest(){
        List<String> strings = null;
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings = new ArrayList<>();
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings.add(null);
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings.add("");
        assertFalse(listAndFirstElementIsNotNull(strings));
        strings.set(0, "");
        assertTrue(listAndFirstElementIsNotNull(strings));
    }

    @Test
    public void getListWithDataTest(){
        assertNotNull(getListWithData("String1", "Ionescu", "Stanescu", "Vasiliev"));
        assertNotNull(getListWithData("String1"));
        assertNull(getListWithData());
        assertNull(getListWithData(null));
    }

    @Test
    public void listIsNullOrEmptyTest(){
        assertTrue(listIsNullOrEmpty(null));
        List<String> strings = new ArrayList<>();
        assertTrue(listIsNullOrEmpty(strings));
        strings.add(null);
        assertFalse(listIsNullOrEmpty(strings));
    }

}