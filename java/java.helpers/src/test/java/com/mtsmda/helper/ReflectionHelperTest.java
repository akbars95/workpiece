package com.mtsmda.helper;

import com.mtsmda.search.common.model.Person;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static org.testng.Assert.*;

/**
 * Created by dminzat on 9/1/2016.
 */
public class ReflectionHelperTest {

    private Person person = new Person("firstNameIon", "lastNameIonescu");

    @Test
    public void getFieldTest() {
        try {
            Field firstName = ReflectionHelper.getField(this.person, "firstName");
            assertNotNull(firstName);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getFieldsTest(){
        Field[] fields = ReflectionHelper.getFields(this.person);
        assertNotNull(fields);
        assertEquals(fields.length, 2);
    }

    @Test
    public void isFieldInClassTest() {
        assertTrue(ReflectionHelper.isFieldInClass(person, "firstName"), "this field is in class");
        assertFalse(ReflectionHelper.isFieldInClass(person, "name"), "this field is not in class");
    }

    @Test
    public void isFieldValueInClassTest() {
        assertNotNull(ReflectionHelper.getFieldValueInClass(person, "firstName"));
    }

}

