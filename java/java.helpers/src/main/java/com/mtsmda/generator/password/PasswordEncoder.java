package com.mtsmda.generator.password;

import com.mtsmda.generator.GenerateRandom;
import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.helper.StringHelper;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dminzat on 10/22/2016.
 */
public class PasswordEncoder {

    private static final Map<Integer, List<String>> numberAndLetters = new LinkedHashMap<>();

    static {
        /*a	b	c	d	e	f	g	h	i	j	k	l	m	n	o	p	q	r	s	t	u	v	w	x	y	z
        1 А Б В abc
        2 Г Д Е def
        3 Ё Ж З ghi
        4 И Й К jkl
        5 Л М Н mno
        6 О П Р С pqr
        7 Т У Ф Х stu
        8 Ц Ч Ш Щ vw
        9 Ъ Ы Ь xy
        0 Э Ю Я z
         */
        List<String> number1 = ListHelper.getListWithData("A", "a", "b", "B", "c", "C", "А", "а", "Б", "б", "в", "В");
        List<String> number2 = ListHelper.getListWithData("D", "d", "E", "e", "f", "F", "Г", "г", "Д", "д", "Е", "е");
        List<String> number3 = ListHelper.getListWithData("G", "g", "H", "h", "i", "I", "Ё", "ё", "ж", "Ж", "з", "З");
        List<String> number4 = ListHelper.getListWithData("j", "J", "K", "k", "L", "l", "И", "и", "Й", "й", "к", "К");
        List<String> number5 = ListHelper.getListWithData("m", "M", "N", "n", "o", "O", "Л", "л", "м", "М", "н", "Н");
        List<String> number6 = ListHelper.getListWithData("p", "P", "Q", "q", "R", "r", "О", "о", "п", "П", "р", "Р");
        List<String> number7 = ListHelper.getListWithData("S", "s", "t", "T", "U", "u", "Т", "т", "у", "У", "Ф", "ф");
        List<String> number8 = ListHelper.getListWithData("V", "v", "w", "W", "Х", "х", "Ц", "ц", "ч", "Ч", "ш", "ш");
        List<String> number9 = ListHelper.getListWithData("X", "x", "Y", "y", "Щ", "щ", "Ъ", "ъ", "ы", "Ы");
        List<String> number0 = ListHelper.getListWithData("Z", "z", "Ь", "ь", "Э", "э", "ю", "Ю", "Я", "я");
        numberAndLetters.put(0, number0);
        numberAndLetters.put(1, number1);
        numberAndLetters.put(2, number2);
        numberAndLetters.put(3, number3);
        numberAndLetters.put(4, number4);
        numberAndLetters.put(5, number5);
        numberAndLetters.put(6, number6);
        numberAndLetters.put(7, number7);
        numberAndLetters.put(8, number8);
        numberAndLetters.put(9, number9);
    }

    public static String encodePassword(String rawPassword, EncoderType encoderType) {
        if (StringUtils.isBlank(rawPassword)) {
            throw new RuntimeException("Input password is null or empty!");
        }
        StringBuilder encodedPassword = new StringBuilder().append(rawPassword);

        switch (encoderType) {
            case REVERSE: {
                reverseEncodeDecode(encodedPassword);
            }
            break;
            case TO_LOWER_CASE: {
                toLowerUpperCaseEncode(encodedPassword, true);
            }
            break;
            case TO_UPPER_CASE: {
                toLowerUpperCaseEncode(encodedPassword, false);
            }
            break;
            case PHONE_TYPE_CUSTOM: {
                phoneEncode(encodedPassword);
            }
            break;
        }

        randomLetters(encoderType, encodedPassword, 1);
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

        randomLetters(encoderType, decodedPassword, 0);
        switch (encoderType) {
            case REVERSE: {
                reverseEncodeDecode(decodedPassword);
            }
            break;
            case TO_LOWER_CASE: {
                toLowerUpperCaseDecode(decodedPassword, true);
            }
            break;
            case TO_UPPER_CASE: {
                toLowerUpperCaseDecode(decodedPassword, false);
            }
            break;
        }

        return decodedPassword.toString();
    }

    /*phone encode*/
    private static void phoneEncode(StringBuilder encodedPassword){
        String temp = encodedPassword.toString();
        encodedPassword.delete(0, encodedPassword.length());
        for(int i = 0; i < temp.length(); i++){
            Character current = temp.charAt(i);
            if(Character.isLetter(current)){
                encodedPassword.append(getPhoneTypeNumbers(current + ""));
            }else{
                encodedPassword.append(current).append("|");
            }
        }
    }

    /*to lower upper case*/
    private static void toLowerUpperCaseEncode(StringBuilder encodedPassword, boolean isLower) {
        String temp = encodedPassword.toString();
        List<Integer> indexis = new ArrayList<>();
        for (int i = 0; i < temp.length(); i++) {
            char currentChar = temp.charAt(i);
            if (Character.isLetter(currentChar) && (isLower && Character.isUpperCase(currentChar)) ||
                    (!isLower && Character.isLowerCase(currentChar))) {
                indexis.add(i);
            }
        }
        encodedPassword.delete(0, encodedPassword.length());
        if (isLower) {
            encodedPassword.append(temp.toLowerCase());
        } else {
            encodedPassword.append(temp.toUpperCase());
        }
        encodedPassword.append("sE").append(temp.length())
                .append("Ct").append(indexis.size()).append("In")
                .append(ListHelper.getListAsStringWithDelimiter(indexis, null));
    }

    private static void toLowerUpperCaseDecode(StringBuilder decodedPassword, boolean isLower) {
        //ivanovsE6Ct1In0
        String passwordLength = decodedPassword.substring(decodedPassword.lastIndexOf("sE") + 2, decodedPassword.lastIndexOf("Ct"));
        String indexisSize = decodedPassword.substring(decodedPassword.lastIndexOf("Ct") + 2, decodedPassword.lastIndexOf("In"));
        String indexisStr = decodedPassword.substring(decodedPassword.lastIndexOf("In") + 2);

        String password = decodedPassword.subSequence(0, Integer.parseInt(passwordLength)).toString();
        String[] indexisArray = indexisStr.split(",");
        if (Integer.parseInt(indexisSize) != indexisArray.length) {
            throw new RuntimeException("Password cannot decode!");
        }
        for (String currentIndex : indexisArray) {
            int currentIndexInt = Integer.parseInt(currentIndex);
            if (isLower) {
                password = StringHelper.replaceByIndex(password, currentIndexInt,
                        new Character(password.charAt(currentIndexInt)).toString().toUpperCase());
            } else {
                password = StringHelper.replaceByIndex(password, currentIndexInt,
                        new Character(password.charAt(currentIndexInt)).toString().toLowerCase());
            }
        }
        decodedPassword.delete(0, decodedPassword.length());
        decodedPassword.append(password);
    }

    /*reverse*/
    private static void reverseEncodeDecode(StringBuilder decodedPassword) {
        decodedPassword.reverse();
    }

    /* common*/
    private static void addDateAndTime(StringBuilder encodedPassword) {
        LocalDateTime localDateTime = LocalDateTime.now();
        encodedPassword.insert(0, LocalDateTimeHelper.convertLocalTimeToString(localDateTime.toLocalTime(),
                new StringBuilder(LocalDateTimeHelper.SIMPLE_TIME_FORMAT).reverse().toString()));
        encodedPassword.append(LocalDateTimeHelper.convertLocalDateToString(localDateTime.toLocalDate(),
                new StringBuilder(LocalDateTimeHelper.SIMPLE_DATE_FORMAT).reverse().toString()));
    }

    private static void randomLetters(EncoderType encoderType, StringBuilder encodedPassword, int type) {
        GenerateRandom generateRandom = new GenerateRandom(true);
        if (type == 0) {
            encodedPassword.delete(0, encoderType.getBeginCountLetters())
                    .delete(encodedPassword.length() - encoderType.getEndCountLetters(), encodedPassword.length());
        } else {
            encodedPassword.insert(0, generateRandom.generate(encoderType.getBeginCountLetters()))
                    .append(generateRandom.generate(encoderType.getEndCountLetters()));
        }
    }

    private static String getPhoneTypeNumbers(String letter){
        int ik = 0;
        for(Integer key : numberAndLetters.keySet()){
            List<String> strings = numberAndLetters.get(key);
            for(int i = 0; i < strings.size(); i++){
                if(strings.get(i).equals(letter)){
                    return "|" + ik + "_" + key;
                }
            }
            ik++;
        }
        return null;
    }

}