package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;

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

    public static String addLedZero(String raw, int finallyCount){
        if(StringUtils.isBlank(raw)){
            throw new RuntimeException("Input text cannot be blank!");
        }
        if(finallyCount < 1){
            throw new RuntimeException("Finally count should be more than 0!");
        }
        try {
            Long.parseLong(raw);
        }
        catch (NumberFormatException e){
            throw new RuntimeException("number format exception - " + raw);
        }
        if(raw.length() >= finallyCount){
            return raw;
        }
        String result = "";
        for(int i = 0; i < finallyCount - raw.length(); i++){
            result += "0";
        }
        return result += raw;
    }

    public static String deleteLedZero(String input){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            String currentSubStr = input.substring(i);
            if(!currentSubStr.startsWith("0")){
                result.append(currentSubStr);
                break;
            }
        }
        return result.toString();
    }

}