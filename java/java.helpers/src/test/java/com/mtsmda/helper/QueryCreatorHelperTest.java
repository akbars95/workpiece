package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static com.mtsmda.helper.QueryCreatorHelper.*;

/**
 * Created by dminzat on 9/5/2016.
 */
public class QueryCreatorHelperTest {

    private String tableName = "cities";
    private List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");

    @Test
    public void insertGenerateTrueTest() {
        String cities = insertGenerate(tableName, fields);
        assertNotNull(cities);
        assertEquals(cities, INSERT_INTO + " " + tableName + " (city_name,city_country_id) " + VALUES + "(:city_name,:city_country_id);");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestBothParamsIsNull() {
        insertGenerate(null, null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestTableNameIsEmptyFieldsIsNull() {
        insertGenerate("", null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestFieldsIsNull() {
        insertGenerate(this.tableName, null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestFieldIsEmpty() {
        insertGenerate(this.tableName, new ArrayList<String>());
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void insertGenerateFalseTestFieldIsEmpty2() {
        List<String> fields = new ArrayList<>();
        insertGenerate(this.tableName, fields);
    }

    @Test
    public void updateGenerateTrueTest() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate(tableName, fields, "city_id");
        assertNotNull(cities);
        assertEquals(cities, "UPDATE cities SET city_name = :city_name, city_country_id = :city_country_id WHERE city_id = :city_id;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestTableNameIsNull() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate(null, fields, "city_id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestTableNameIsEmpty() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate("", fields, "city_id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestFieldsIsNull() {
        String cities = updateGenerate(this.tableName, null, "city_id");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void updateGenerateFalseTestWhereClauseIsNull() {
        List<String> fields = ListHelper.getListWithData("city_name", "city_country_id");
        String cities = updateGenerate("", fields, null);
    }

    @Test
    public void sameEqualsTrueTest() {
        String cityName = sameEquals("city_name");
        assertNotNull(cityName);
        assertEquals(cityName, " city_name = :city_name");
    }

    @Test
    public void deleteGenerateTrueTest() {
        String cityName = deleteGenerate(this.tableName, "city_name");
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities WHERE city_name = :city_name;");
    }

    @Test
    public void deleteGenerateTrueTestDeleteAll() {
        String cityName = deleteGenerate(this.tableName, null);
        assertNotNull(cityName);
        assertEquals(cityName, "DELETE FROM cities;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void deleteGenerateFalseTestTableNameIsNull() {
        String cityName = deleteGenerate(null, "city_name");
    }

    @Test
    public void selectByIdTrueTest() {
        String selectById = selectById(this.tableName, this.fields.get(0));
        assertNotNull(selectById);
        assertEquals(selectById, "SELECT * FROM cities WHERE city_name = :city_name;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void selectByIdFalseTestTableNameIsNull() {
        String selectById = selectById(null, this.fields.get(0));
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void selectByIdFalseTestWhereClauseIsNull() {
        String selectById = selectById(this.tableName, null);
    }

    @Test
    public void getAliasTrueTest() {
        String alias = getAlias(this.tableName);
        assertNotNull(alias);
        assertEquals(alias, "c");
    }

    @Test
    public void selectAllTrueTest() {
        String selectAll = selectAll(this.tableName);
        assertNotNull(selectAll);
        assertEquals(selectAll, "SELECT * FROM cities;");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void selectAllFalseTestTableNameIsNull() {
        String selectAll = selectAll(null);
    }

}