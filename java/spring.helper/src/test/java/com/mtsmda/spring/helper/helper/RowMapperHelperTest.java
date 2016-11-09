package com.mtsmda.spring.helper.helper;

import org.testng.annotations.Test;

import static com.mtsmda.spring.helper.helper.RowMapperHelper.*;
import static org.testng.Assert.*;
/**
 * Created by MTSMDA on 09.11.2016.
 */
public class RowMapperHelperTest {

    @Test
    public void getTest(){
        String str = get("Str");
        assertNotNull(str);
        assertEquals("Str", str);
    }

}