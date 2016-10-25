package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by dminzat on 10/24/2016.
 */
public class SetHelper {

    public static <T> boolean isNotNullAndNotEmpty(Set<T> tSet) {
        return ObjectHelper.objectIsNotNull(tSet) && !tSet.isEmpty();
    }

    public static String convertSetToStringWithDelimiter(Set<?> set, String delimiter) {
        return convertSetToStringWithDelimiter(set, delimiter, null, false);
    }

    public static String convertSetToStringWithDelimiter(Set<?> set, String delimiter, String prefix, boolean firstElementWithPrefix) {
        StringBuilder stringBuilder = new StringBuilder();
        final String delimiterInner = StringUtils.isNotBlank(delimiter) ? delimiter : ",";
        final String prefixInner = StringUtils.isNotBlank(prefix) ? prefix : "";
        set.forEach(o -> {
            stringBuilder.append(prefixInner).append(o).append(delimiterInner);
        });
        if (StringUtils.isNotBlank(prefix) && !firstElementWithPrefix && stringBuilder.toString().startsWith(prefixInner)) {
            stringBuilder.delete(0, prefixInner.length());
        }
        if (stringBuilder.toString().endsWith(delimiterInner)) {
            stringBuilder.delete(stringBuilder.lastIndexOf(delimiterInner), stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public static <T> List<T> convertSetToList(Set<T> tSet) {
        List<T> tList = new ArrayList<>();
        tSet.forEach(current -> {
            tList.add(current);
        });
        return tList;
    }

    public static <T> Set<T> createSet(T... ts) {
        Set<T> tSet = new LinkedHashSet<T>();
        for (T current : ts) {
            tSet.add(current);
        }
        return tSet;
    }

}