package com.mtsmda.pattern;

/**
 * Created by dminzat on 9/1/2016.
 */
public class Patterns {

    public static final String USERNAME = "^[\\w-\\.]{7,50}$";
    public static final String PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%-.,]).{5,50})";
    public static final String FIRST_LAST_MIDDLE_NAME = "^[\\w-']{1,75}$";
    public static final String GENDER = "^([Mm]|[Ff]){1,1}$";

}