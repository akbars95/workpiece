package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dminzat on 10/24/2016.
 */
public class SetHelper {

    public static <T> boolean isNotNullAndNotEmpty(Set<T> tSet) {
        return ObjectHelper.objectIsNotNull(tSet) && !tSet.isEmpty();
    }

    public static String convertSetToStringWithDelimiter(Set<?> set, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        final String delimiterInner = StringUtils.isNotBlank(delimiter) ? delimiter : ",";
        set.forEach(o -> {
            stringBuilder.append(o).append(delimiterInner);
        });
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
        Set<T> tSet = new HashSet<>();
        for (T current : ts) {
            tSet.add(current);
        }
        return tSet;
    }

}