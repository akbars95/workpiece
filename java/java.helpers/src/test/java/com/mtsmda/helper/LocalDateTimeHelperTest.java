package com.mtsmda.helper;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import static org.testng.Assert.*;
import static com.mtsmda.helper.LocalDateTimeHelper.*;

/**
 * Created by MTSMDA on 01.09.2016.
 */
public class LocalDateTimeHelperTest {

    private LocalDate localDate = LocalDate.of(2010, 10, 11);
    private LocalTime localTime = LocalTime.of(15, 20, 30);
    private LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

    @Test
    public void localDateTest(){
        assertEquals(localDate(this.localDate, "ddMMyyyy"), "11102010");
        assertNotEquals(localDate(this.localDate, "ddMMyyyy"), "111020.10");
    }

    @Test
    public void localTimeTest(){
        assertEquals(localTime(this.localTime, "HHmmss"), "152030");
        assertNotEquals(localTime(this.localTime, "HHmmss"), "111020.10");
    }

    @Test
    public void localDateTimeTest(){
        assertEquals(localDateTime(localDateTime, "yyyyMMdd HHmmss"), "20101011 152030");
        assertNotEquals(localDateTime(localDateTime, "yyyyMMdd HH:mmss"), "20101011 152030");
    }

    @Test
    public void localDateMySqlFormatTest(){
        assertEquals(localDateMySqlFormat(this.localDate), "2010-10-11");
        assertNotEquals(localDateMySqlFormat(this.localDate), "2010.10.11");
    }

    @Test
    public void localTimeMySqlFormatTest(){
        assertEquals(localTimeMySqlFormat(this.localTime), "15:20:30");
        assertNotEquals(localTimeMySqlFormat(this.localTime), "2010.10.11");
    }

    @Test
    public void localDateTimeMySqlFormatTest(){
        assertEquals(localDateTimeMySqlFormat(this.localDateTime), "2010-10-11 15:20:30");
        assertNotEquals(localDateTimeMySqlFormat(this.localDateTime), "2010-10-1115:20:30");
    }

    @Test
    public void parseLocalDateTimeMySqlFormatTest(){
        assertTrue(this.localDateTime.equals(parseLocalDateTimeMySqlFormat("2010-10-11 15:20:30")));
        assertFalse(this.localDateTime.equals(parseLocalDateTimeMySqlFormat("2010-10-11 15:20:31")));
    }

    @Test
    public void convertDateToLocalDateTimeTest(){
        assertNotNull(convertDateToLocalDateTime(new Date()));
    }

    @Test
    public void getLocalDateTest(){
        assertNull(getLocalDate(null));
        assertNull(getLocalDate("sdfsdf"));
        assertNotNull(getLocalDate("10.10.2014"));
        System.out.println(getLocalDate("10.10.2014"));
    }

    @Test
    public void getLocalTimeTest(){
        assertNull(getLocalTime(null));
        assertNull(getLocalTime("adas"));
        assertNull(getLocalTime("123456789"));
        assertNull(getLocalTime("1234567"));
        LocalTime localTime = getLocalTime("10:15:25");
        assertNotNull(localTime);
        assertEquals(LocalTime.of(10, 15, 25), localTime);
    }

    @Test
    public void getLocalDateTimeTest(){
        assertNull(getLocalDateTime("fwsd0fds0fds"));
        LocalDateTime localDateTime = getLocalDateTime("10:09:35 17.02.2009");
        assertNotNull(localDateTime);
        assertEquals(localDateTime, LocalDateTime.of(2009, 2, 17, 10, 9, 35));
    }

}