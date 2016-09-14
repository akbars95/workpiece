package com.mtsmda.helper;

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

}