package com.mtsmda.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dminzat on 9/2/2016.
 */
public class MapHelper {

    public static <K, V> Map<K, V> getMap(List<K> kList, List<V> vList) {
        Map<K, V> kvMap = null;
        if (ObjectHelper.objectIsNotNull(kList) && ObjectHelper.objectIsNotNull(vList) && kList.size() == vList.size()) {
            kvMap = new LinkedHashMap<K, V>();
            for (int i = 0; i < kList.size(); i++) {
                kvMap.put(kList.get(i), vList.get(i));
            }
        }
        return kvMap;
    }

    public static <K, V> Map<K, V> getMap(K[] kArray, V[] vArray) {
        Map<K, V> kvMap = null;
        if (ObjectHelper.objectIsNotNull(kArray) && ObjectHelper.objectIsNotNull(vArray) && kArray.length == vArray.length) {
            kvMap = new LinkedHashMap<K, V>();
            for (int i = 0; i < kArray.length; i++) {
                kvMap.put(kArray[i], vArray[i]);
            }
        }
        return kvMap;
    }


}