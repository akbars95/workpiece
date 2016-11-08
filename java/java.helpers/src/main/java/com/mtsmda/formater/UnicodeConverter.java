package com.mtsmda.formater;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by dminzat on 11/7/2016.
 *
 * @link github.com/akbars95/helperPrograms03052015/blob/master/ConvertUTF8/src/com/mtsmda/logic/UnicodeConverter.java#L25
 */
public class UnicodeConverter {

    public static String nativeToUTF8(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char aChar = text.charAt(i);
            if ((aChar < 0x0020) || (aChar > 0x007e)) {
                sb.append('\\');
                sb.append('u');
                sb.append(toHex((aChar >> 12) & 0xF));
                sb.append(toHex((aChar >> 8) & 0xF));
                sb.append(toHex((aChar >> 4) & 0xF));
                sb.append(toHex(aChar & 0xF));
            } else {
                sb.append(aChar);
            }
        }
        return sb.toString();
    }

    public static String unNative2Ascii(String textIn) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textIn.length(); i++) {
            char aChar = textIn.charAt(i);
            sb.append(aChar);
        }
        return sb.toString();
    }

    private static char toHex(int nibble) {
        final char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return hexDigit[nibble & 0xF];
    }

    /*public static void main(String[] args) {
        System.out.println(UnicodeConverter.nativeToUTF8("Барселона"));
        System.out.println(UnicodeConverter
                .unNative2Ascii("\u041C\u044B\u043D\u0437\u0430\u0442"));
        System.out.println(UnicodeConverter.nativeToUTF8(
                "\u041C\u044B\u043D\u0437\u0430\u0442"));
    }*/

}