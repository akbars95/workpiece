package com.mtsmda.db;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dminzat on 10/23/2016.
 */
public class Db {

    private String tableName;
    private List<String> tableFields = new ArrayList<>();
    private Map<String, List<String>> fieldNameAndCheckedDatas = new LinkedHashMap<>();

}