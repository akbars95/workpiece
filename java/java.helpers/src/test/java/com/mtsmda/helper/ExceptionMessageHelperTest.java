package com.mtsmda.helper;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static com.mtsmda.helper.ExceptionMessageHelper.*;

/**
 * Created by dminzat on 9/19/2016.
 */
public class ExceptionMessageHelperTest {

    @Test(expectedExceptions = {RuntimeException.class})
    public void repositoryExpectedExceptionTest() {
        repositoryExpectedExceptionProcess();
    }

    @Test
    public void repositoryExpectedExceptionMessageTest() {
        try {
            repositoryExpectedExceptionProcess();
        } catch (RuntimeException e) {
            assertEquals(e.getClass().getCanonicalName(), RuntimeException.class.getCanonicalName());
            assertEquals(e.getMessage(), "REPOSITORY:delete;This is message");
        }
    }

    @Test
    public void repositoryExpectedExceptionMessageIsNullTest() {
        try {
            repository(null, DELETE_OP);
        } catch (RuntimeException e) {
            assertEquals(e.getClass().getCanonicalName(), RuntimeException.class.getCanonicalName());
            assertEquals(e.getMessage(), "REPOSITORY:delete;");
        }
    }

    @Test
    public void repositoryExpectedExceptionMessageIsNullOperationIsNullTest() {
        try {
            repository(null, null);
        } catch (RuntimeException e) {
            assertEquals(e.getClass().getCanonicalName(), RuntimeException.class.getCanonicalName());
            assertEquals(e.getMessage(), "REPOSITORY:;");
        }
    }

    private void repositoryExpectedExceptionProcess() {
        repository("This is message", DELETE_OP);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void exceptionHandleExpectedExceptionTest() {
        exceptionHandleProcess();
    }

    @Test
    public void exceptionHandleTest() {
        try {
            exceptionHandleProcess();
        } catch (RuntimeException e) {
            assertEquals(e.getClass().getCanonicalName(), RuntimeException.class.getCanonicalName());
            assertEquals(e.getMessage(), RuntimeException.class.getCanonicalName() + ";" + "Message");
        }
    }

    private void exceptionHandleProcess() {
        exceptionHandle(new RuntimeException("Message"));
    }

    @Test
    public void exceptionDescriptionExceptionParamIsNullTest() {
        String descriptionProcess = exceptionDescription(null);
        assertNotNull(descriptionProcess);
        assertEquals(descriptionProcess, "NULL_EXCEPTION_OBJECT!;");
    }

    @Test
    public void exceptionDescriptionTest() {
        String exceptionMessage = "Message";
        String descriptionProcess = exceptionDescription(new RuntimeException(exceptionMessage));
        assertNotNull(descriptionProcess);
        assertEquals(descriptionProcess, RuntimeException.class.getCanonicalName() + ";" + exceptionMessage);
    }


}