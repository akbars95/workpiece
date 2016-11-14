package com.mtsmda.manipulation;

/**
 * Created by dminzat on 11/14/2016.
 */
public interface Convertible<T, IN> {

    T convert(IN inputObject);

}