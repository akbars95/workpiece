package com.mtsmda.helper;

/**
 * Created by MTSMDA on 25.10.2016.
 */
public class StringHelper {

    /*
    * This is source
    * 5
    * Name
    * This Names source
    * */
    public static String replaceByIndex(String source, int index, String change){
        StringBuilder result  = new StringBuilder();
        String start = source.substring(0, index);
        String end = source.substring(index + 1);
        result.append(start).append(change).append(end);
        return result.toString();
    }

}