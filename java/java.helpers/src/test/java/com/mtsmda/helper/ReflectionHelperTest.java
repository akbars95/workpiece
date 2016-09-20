package com.mtsmda.helper;

import com.mtsmda.search.common.model.Manager;
import com.mtsmda.search.common.model.Person;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

import static com.mtsmda.helper.ReflectionHelper.*;
import static org.testng.Assert.*;

/**
 * Created by dminzat on 9/1/2016.
 */
public class ReflectionHelperTest {

    private Person person = new Person("firstNameIon", "lastNameIonescu");
    private Manager manager = new Manager(person.getFirstName(), person.getLastName(), "H2Manager");

    @Test
    public void getFieldTest() {
        try {
            Field firstName = getField(this.person, "firstName");
            assertNotNull(firstName);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getFieldsTest() {
        Field[] fields = getFields(this.person);
        assertNotNull(fields);
        assertEquals(fields.length, 2);
    }

    @Test
    public void isFieldInClassTest() {
        assertTrue(isFieldInClass(person, "firstName"), "this field is in class");
        assertFalse(isFieldInClass(person, "name"), "this field is not in class");
    }

    @Test
    public void isFieldValueInClassTest() {
        assertNotNull(getFieldValueInClass(person, "firstName"));
    }

    @Test
    public void getFieldInheritanceTest() {
        try {
            Field firstName = getField(this.manager, "firstName");
            assertNotNull(firstName);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getFieldInheritanceTrueTest() {
        try {
            Field firstName = getField(this.manager, "managerName");
            assertNotNull(firstName);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getFieldInheritanceFalseTest() throws NoSuchFieldException {
        Field firstName = getField(this.manager, "patronymicName");
        assertNull(firstName);
    }

}

