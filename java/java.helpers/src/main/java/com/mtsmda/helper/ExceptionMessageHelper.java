package com.mtsmda.helper;

/**
 * Created by dminzat on 9/5/2016.
 */
public class ExceptionMessageHelper {

    public static final String INSERT_OP = "insert";
    public static final String UPDATE_OP = "update";
    public static final String DELETE_OP = "delete";
    public static final String GET_OP = "get";
    public static final String GET_ALL_OP = GET_OP + "All";

    public static void repository(String message, String operation) {
        throw new RuntimeException("REPOSITORY:" + (ObjectHelper.objectIsNotNull(operation) ? operation : "")
                + ";" + (ObjectHelper.objectIsNotNull(message) ? message : ""));
    }

    public static void exceptionHandle(Exception e) {
        throw new RuntimeException(e.getClass().getCanonicalName() + ";" +
                (ObjectHelper.objectIsNull(e.getMessage()) ? "" : e.getMessage()));
    }

    public static String exceptionDescription(Exception e) {
        return ((ObjectHelper.objectIsNotNull(e) ? e.getClass().getCanonicalName() : "NULL_EXCEPTION_OBJECT!") + ";"
                + ((ObjectHelper.objectIsNotNull(e) && ObjectHelper.objectIsNotNull(e.getMessage())) ? e.getMessage() : ""));
    }

}