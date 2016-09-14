package com.mtsmda.search.common;

import com.mtsmda.helper.ListHelper;
import com.mtsmda.helper.MapHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mtsmda.pattern.Patterns.*;

/**
 * Created by dminzat on 9/2/2016.
 */
public class PatternsTest {

    private static Pattern pattern;
    private static Matcher matcher;

    private List<String> listData;
    private List<Boolean> listResult;

    public static boolean validate(final String username) {
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public void init(final String patternStr) {
        pattern = Pattern.compile(patternStr);
    }

    @Test
    public void usernamePatternTest() {
        init(USERNAME);
        this.listData = ListHelper.getListWithData("120", "1234567",
                "014785236901478523690147852369014785236901478523691", "ivanov.ivan", "dfdsk-/");
        this.listResult = ListHelper.getListWithData(false, true, false, true, false);
        MapHelper.getMap(listData, listResult).forEach((k, v) -> {
            if (v) {
                Assert.assertTrue(validate(k), "key - " + k);
            } else {
                Assert.assertFalse(validate(k), "key - " + k);
            }
        });
    }

    @Test
    public void passwordPatternTest() {
        init(PASSWORD);
        this.listData = ListHelper.getListWithData("120", "1234567",
                "014785236901478523690147852369014785236901478523691", "ivanov.ivan", "dfdsk-/", "Ivan18#"
                , "IonescuPopa.Ion");
        this.listResult = ListHelper.getListWithData(false, false, false, false, false, true, false);
        MapHelper.getMap(listData, listResult).forEach((k, v) -> {
            if (v) {
                Assert.assertTrue(validate(k), "key - " + k);
            } else {
                Assert.assertFalse(validate(k), "key - " + k);
            }
        });
    }

    @Test
    public void firstLastMiddleNamePatternTest() {
        init(FIRST_LAST_MIDDLE_NAME);
        this.listData = ListHelper.getListWithData("120", "1234567",
                "014785236901478523690147852369014785236901478523691", "ivanov.ivan", "dfdsk-/", "Ivan18#"
                , "IonescuPopa.Ion", "");
        this.listResult = ListHelper.getListWithData(true, true,
                true, false, false, false,
                false, false);
        MapHelper.getMap(listData, listResult).forEach((k, v) -> {
            if (v) {
                Assert.assertTrue(validate(k), "key - " + k);
            } else {
                Assert.assertFalse(validate(k), "key - " + k);
            }
        });
    }
    @Test
    public void genderPatternTest() {
        init(GENDER);
        this.listData = ListHelper.getListWithData("120", "1234567",
                "014785236901478523690147852369014785236901478523691", "", "VC",
                "m", "M", "F", "f", "MF");
        this.listResult = ListHelper.getListWithData(false, false, false, false, false,
                true, true, true, true, false);
        MapHelper.getMap(listData, listResult).forEach((k, v) -> {
            if (v) {
                Assert.assertTrue(validate(k), "key - " + k);
            } else {
                Assert.assertFalse(validate(k), "key - " + k);
            }
        });
    }

}