package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by dminzat on 9/2/2016.
 */
public class MapHelperTest {

    @Test
    public void getMapList(){
        List<Integer> integers = ListHelper.getListWithData(1, 19, 253, 75, 325);
        List<String> strings = ListHelper.getListWithData("One", "nineteen",
                "two hundred three", "seventy five", "three hundred twenty five");
        assertNotNull(MapHelper.getMap(integers, strings));
        strings.add("eight");
        assertNull(MapHelper.getMap(integers, strings));
        strings.remove(0);
        assertNotNull(MapHelper.getMap(integers, strings));
        strings = null;
        assertNull(MapHelper.getMap(integers, strings));
    }

    @Test
    public void getMapArray(){
        String[] footballPlayersName = {"Messi", "Henry", "Kaka"};
        Integer[] footballPlayersNumber = {10, 14, 22};

        assertNotNull(MapHelper.getMap(footballPlayersName, footballPlayersNumber));
        footballPlayersName = null;
        assertNull(MapHelper.getMap(footballPlayersName, footballPlayersNumber));
    }

}