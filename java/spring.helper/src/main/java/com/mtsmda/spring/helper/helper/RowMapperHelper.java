package com.mtsmda.spring.helper.helper;

/**
 * Created by dminzat on 11/9/2016.
 */
public class RowMapperHelper {

    public static <T> T get(T ob){
        try{
            return ob;
        }
        catch (Exception e){
            return null;
        }
    }

}