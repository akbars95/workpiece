package com.mtsmda.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by dminzat on 9/5/2016.
 */
public class QueryCreatorHelper {
    public static final String INSERT_INTO = "INSERT INTO";
    public static final String SPACE = " ";
    public static final String OPEN_PARENTHESIS = "(";
    public static final String CLOSE_PARENTHESIS = ")";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String VALUES = "VALUES";
    public static final String SEMICOLON = ";";
    public static final String UPDATE = "UPDATE";
    public static final String SET = "SET";
    public static final String EQUAL = "=";
    public static final String WHERE = "WHERE";
    public static final String DELETE_FROM = "DELETE FROM";
    public static final String SELECT = "SELECT";
    public static final String STAR = "*";
    public static final String SELECT_ALL = SELECT + SPACE + STAR;
    public static final String FROM = "FROM";
    public static final String DOT = ".";
    public static final String AND = "AND";
    public static final String OR = "OR";

    /*
    * insert into cities (city_name, city_country_id) values(:city_name, city_country_id);
    * */
    public static String insertGenerate(String tableName, List<String> fieldNames) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        ObjectHelper.objectIsNullThrowException(fieldNames);
        if (ListHelper.listIsNullOrEmpty(fieldNames)) {
            throw new RuntimeException("list is null or empty");
        }

        StringBuilder sbResult = new StringBuilder(INSERT_INTO);
        sbResult.append(SPACE).append(tableName).append(SPACE).append(OPEN_PARENTHESIS);
        StringBuilder temp = new StringBuilder();
        fieldNames.forEach(fieldName -> {
            ObjectHelper.objectIsNullThrowException(fieldName);
            sbResult.append(fieldName).append(COMMA);
            temp.append(COLON).append(fieldName).append(COMMA);
        });
        checkEndCommaSymbolAndDeleteHim(sbResult);
        checkEndCommaSymbolAndDeleteHim(temp);
        sbResult.append(CLOSE_PARENTHESIS).append(SPACE).append(VALUES).append(OPEN_PARENTHESIS).append(temp)
                .append(CLOSE_PARENTHESIS).append(SEMICOLON);

        return sbResult.toString();
    }

    /*
    * update cities set city_name = :city_name, city_country_id = :city_country_id where city_id = :city_id;
    * */
    public static String updateGenerate(String tableName, List<String> fieldNamesForChange, String whereClause) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        if (StringUtils.isBlank(whereClause)) {
            throw new RuntimeException("where clause is null or empty!");
        }
        ObjectHelper.objectIsNullThrowException(fieldNamesForChange);
        if (ListHelper.listIsNullOrEmpty(fieldNamesForChange)) {
            throw new RuntimeException("list is null or empty");
        }

        StringBuilder sbResult = new StringBuilder(UPDATE);
        sbResult.append(SPACE).append(tableName).append(SPACE).append(SET);
        fieldNamesForChange.forEach(fieldName -> {
            ObjectHelper.objectIsNullThrowException(fieldName);
            sbResult.append(sameEquals(fieldName)).append(COMMA);
        });
        checkEndCommaSymbolAndDeleteHim(sbResult);
        sbResult.append(SPACE).append(WHERE).append(sameEquals(whereClause))
                .append(SEMICOLON);

        return sbResult.toString();
    }

    /*
    * delete from cities where city_id = :city_id;
    * if @param whereClause is null should be - delete from cities;
    * */
    public static String deleteGenerate(String tableName, String whereClause) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        StringBuilder sbResult = new StringBuilder(DELETE_FROM);
        sbResult.append(SPACE).append(tableName);
        if (StringUtils.isNotBlank(whereClause)) {
            sbResult.append(SPACE).append(WHERE).append(sameEquals(whereClause));
        }
        sbResult.append(SEMICOLON);
        return sbResult.toString();
    }

    /*
    *  city_id = :city_id
    * */
    public static String sameEquals(String fieldName) {
        return new StringBuilder(SPACE).append(fieldName).append(SPACE).append(EQUAL).append(SPACE).
                append(COLON).append(fieldName).toString();
    }

    /*
    * select * from cities c where city_id = :city_id;
    * */
    public static String selectById(String tableName, String whereClause) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        if (StringUtils.isBlank(whereClause)) {
            throw new RuntimeException("where clause is null or empty!");
        }
        StringBuilder sbResult = new StringBuilder(SELECT_ALL);
        sbResult.append(SPACE).append(FROM).append(SPACE).append(tableName).append(SPACE);
        sbResult.append(WHERE).append(sameEquals(whereClause)).append(SEMICOLON);

        return sbResult.toString();
    }

    public static String getAlias(String tableName) {
        return new Character(tableName.charAt(0)).toString().toLowerCase();
    }

    /*
    * select * from cities;
    * */
    public static String selectAll(String tableName) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        StringBuilder sbResult = new StringBuilder(SELECT_ALL);
        sbResult.append(SPACE).append(FROM).append(SPACE).append(tableName).append(SEMICOLON);

        return sbResult.toString();
    }

    /*
    * select c.name, c.age from cities c where c.id = :id
    * */
    public static String selectCustom(List<String> selectParams, String tableName, List<String> whereParam, boolean isAnd) {
        if (StringUtils.isBlank(tableName)) {
            throw new RuntimeException("table name is null or empty!");
        }
        final StringBuilder sbResult = new StringBuilder();
        String alias = getAlias(tableName);
        if (ListHelper.listIsNullOrEmpty(selectParams)) {
            sbResult.append(SELECT_ALL).append(SPACE);
        } else {
            sbResult.append(SELECT).append(SPACE);
            selectParams.forEach(selectParam -> {
                if (StringUtils.isBlank(selectParam)) {
                    throw new RuntimeException("Select param is null or empty!");
                }
                sbResult.append(alias).append(DOT).append(selectParam).append(COMMA);
            });
            if(sbResult.toString().endsWith(COMMA)){
                String temp = sbResult.substring(0, sbResult.length() - 1);
                sbResult.delete(0, sbResult.length()).append(temp);
            }
            sbResult.append(SPACE);
        }
        checkEndCommaSymbolAndDeleteHim(sbResult);
        sbResult.append(FROM).append(SPACE).append(tableName).append(SPACE).append(alias);

        if (ListHelper.listIsNotNullAndNotEmpty(whereParam)) {
            sbResult.append(SPACE).append(WHERE);
            whereParam.forEach(currentWhereParam -> {
                sbResult.append(SPACE).append(alias).append(DOT).append(currentWhereParam).append(SPACE).append(EQUAL).append(SPACE)
                        .append(COLON).append(currentWhereParam);
                if (isAnd) {
                    sbResult.append(SPACE).append(AND);
                } else {
                    sbResult.append(SPACE).append(OR);
                }
            });
            String tempResult = sbResult.toString();
            if (isAnd) {
                if(tempResult.endsWith(AND)){
                    return sbResult.substring(0, sbResult.length() - 3).trim();
                }
            } else {
                if(tempResult.endsWith(OR)){
                    return sbResult.substring(0, sbResult.length() - 2).trim();
                }
            }
        }
        return sbResult.toString().trim();
    }

    private static boolean checkEndCommaSymbol(StringBuilder textQuery) {
        return new Character(textQuery.charAt(textQuery.length() - 1)).toString().equals(COMMA);
    }

    private static void checkEndCommaSymbolAndDeleteHim(StringBuilder textQuery) {
        if (checkEndCommaSymbol(textQuery)) {
            textQuery.deleteCharAt(textQuery.length() - 1);
        }
    }


}