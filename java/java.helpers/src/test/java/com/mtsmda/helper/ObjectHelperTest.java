package com.mtsmda.helper;

/**
 * Created by dminzat on 9/2/2016.
 */
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ObjectHelperTest {

    @Test
    public void objectIsNullThrowExceptionTest(){
        ObjectHelper.objectIsNullThrowException(new Object());
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void objectIsNullThrowExceptionThrowExceptionTest(){
        ObjectHelper.objectIsNullThrowException(null);
    }

    @Test
    public void objectIsNullTest(){
        assertTrue(ObjectHelper.objectIsNull(null));
        assertFalse(ObjectHelper.objectIsNull(new String()));
    }

    @Test
    public void objectIsNotNullTest(){
        assertTrue(ObjectHelper.objectIsNotNull(new String()));
        assertFalse(ObjectHelper.objectIsNotNull(null));
    }

}