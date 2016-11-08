package com.mtsmda.formater;

import com.mtsmda.helper.ListHelper;
import org.testng.annotations.Test;

import java.util.List;

import static com.mtsmda.formater.UnicodeConverter.*;
import static org.testng.Assert.*;
/**
 * Created by dminzat on 11/7/2016.
 */
public class UnicodeConverterTest {

    @Test
    public void native2AsciiTest(){
        List<String> listWithData = ListHelper.getListWithData("Ion", "Барселона", "gmail.com");
        listWithData.forEach(current ->{
            String nativeText = nativeToUTF8(current);
            System.out.println(current + " = " + nativeText);
            assertNotNull(nativeText);
        });
    }

    @Test
    public void unNative2AsciiTest(){
        ListHelper.getListWithData("Ion", "\u0411\u0430\u0440\u0441\u0435\u043B\u043E\u043D\u0430", "Mn").forEach(current ->{
            String s = unNative2Ascii(current);
            System.out.println(current + " " + s);
            assertNotNull(s);
        });
    }

}