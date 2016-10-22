package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dminzat on 8/11/2016.
 */
public class ListHelper {

    public static <T> boolean listIsNotNullAndNotEmpty(List<T> tList) {
        return !ObjectHelper.objectIsNull(tList) && !tList.isEmpty();
    }

    public static <T> boolean listAndFirstElementIsNotNull(List<T> tList) {
        return listIsNotNullAndNotEmpty(tList) && !ObjectHelper.objectIsNull(tList.get(0));
    }

    public static <T> boolean listIsNullOrEmpty(List<T> tList) {
        return ObjectHelper.objectIsNull(tList) || tList.isEmpty();
    }

    public static <T> List<T> getListWithData(T... ts) {
        List<T> tList = null;
        if (ObjectHelper.objectIsNotNull(ts) && ts.length > 0) {
            tList = new ArrayList<>();
            for (T t : ts) {
                tList.add(t);
            }
        }
        return tList;
    }

    public static <T> String getListAsStringWithDelimiter(List<T> tList, String delimiter) {
        StringBuilder stringBuilder = new StringBuilder();
        tList.forEach(current -> {
            if (StringUtils.isNotBlank(delimiter)) {
                stringBuilder.append(current).append(delimiter);
            } else {
                stringBuilder.append(current).append(",");
            }
        });

        int length = stringBuilder.length();
        if (StringUtils.isNotBlank(delimiter)) {
            if (stringBuilder.toString().endsWith(delimiter)) {
                stringBuilder.delete(length - delimiter.length(), length);
            }
        } else {
            if (stringBuilder.charAt(length - 1) == ',') {
                stringBuilder.delete(length - 1, length);
            }
        }
        return stringBuilder.toString();
    }

}