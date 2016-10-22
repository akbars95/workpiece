package com.mtsmda.generator.password;

import com.mtsmda.generator.GenerateRandom;
import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.LocalDateTimeHelper;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dminzat on 10/22/2016.
 */
public class PasswordEncoder {

    public static String encodePassword(String rawPassword, EncoderType encoderType) {
        if (StringUtils.isBlank(rawPassword)) {
            throw new RuntimeException("Input password is null or empty!");
        }
        StringBuilder encodedPassword = new StringBuilder().append(rawPassword);

        switch (encoderType) {
            case REVERSE: {
                reverseEncode(encodedPassword);
            }
            break;
            case TO_LOWER_CASE: {
                toLowerCaseEncode(encodedPassword);
            }
            break;
        }

        addDateAndTime(encodedPassword);
        return encodedPassword.toString();
    }

    public static String decodePassword(String encodedPassword, EncoderType encoderType) {
        if (StringUtils.isBlank(encodedPassword)) {
            throw new RuntimeException("Input password is null or empty!");
        }
        StringBuilder decodedPassword = new StringBuilder().append(encodedPassword);

        decodedPassword.delete(0, LocalDateTimeHelper.SIMPLE_TIME_FORMAT.length())
                .delete(decodedPassword.length() - LocalDateTimeHelper.SIMPLE_DATE_FORMAT.length(), decodedPassword.length());

        switch (encoderType) {
            case REVERSE: {
                reverseDecode(decodedPassword);
            }
            break;
            case TO_LOWER_CASE: {
//                encodedPassword
            }
            break;
        }

        return decodedPassword.toString();
    }

    /*to lower case*/
    public static void toLowerCaseEncode(StringBuilder encodedPassword) {
        String temp = encodedPassword.toString();
        List<Integer> indexis = new ArrayList<>();
        for (int i = 0; i < temp.length(); i++) {
            char currentChar = temp.charAt(i);
            if (Character.isLetter(currentChar) && Character.isUpperCase(currentChar)) {
                indexis.add(i);
            }
        }
        encodedPassword.delete(0, encodedPassword.length());
        encodedPassword.append(temp.toLowerCase());
        encodedPassword.append(ListHelper.getListAsStringWithDelimiter(indexis, null));
        randomLetters(EncoderType.TO_LOWER_CASE, encodedPassword, 1);
    }

    /*reverse*/
    private static void reverseEncode(StringBuilder encodedPassword) {
        EncoderType reverse = EncoderType.REVERSE;
        encodedPassword.reverse();
        randomLetters(reverse, encodedPassword, 1);
    }

    private static void reverseDecode(StringBuilder decodedPassword) {
        EncoderType reverse = EncoderType.REVERSE;
        randomLetters(reverse, decodedPassword, 0);
        System.out.println(decodedPassword);
        System.out.println();
    }

    /* common*/
    private static void addDateAndTime(StringBuilder encodedPassword) {
        LocalDateTime localDateTime = LocalDateTime.now();
        encodedPassword.insert(0, LocalDateTimeHelper.localTime(localDateTime.toLocalTime(),
                new StringBuilder(LocalDateTimeHelper.SIMPLE_TIME_FORMAT).reverse().toString()));
        encodedPassword.append(LocalDateTimeHelper.localDate(localDateTime.toLocalDate(),
                new StringBuilder(LocalDateTimeHelper.SIMPLE_DATE_FORMAT).reverse().toString()));
    }

    private static void randomLetters(EncoderType encoderType, StringBuilder encodedPassword, int type) {
        GenerateRandom generateRandom = new GenerateRandom(true);
        if (type == 0) {
            encodedPassword.delete(0, encoderType.getBeginCountLetters())
                    .delete(encodedPassword.length() - encoderType.getEndCountLetters(), encodedPassword.length()).reverse();
        } else {
            encodedPassword.insert(0, generateRandom.generate(encoderType.getBeginCountLetters()))
                    .append(generateRandom.generate(encoderType.getEndCountLetters()));
        }
    }

}