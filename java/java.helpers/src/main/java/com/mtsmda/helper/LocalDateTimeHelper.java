package com.mtsmda.helper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by dminzat on 8/12/2016.
 */
public class LocalDateTimeHelper {

    public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MYSQL_TIME_FORMAT = "HH:mm:ss";
    public static final String MYSQL_DATE_TIME_FORMAT = MYSQL_DATE_FORMAT + " " + MYSQL_TIME_FORMAT;
    public static final String NORMAL_DATE_TIME_FORMAT = "dd.MM.yyyy" + " " + MYSQL_TIME_FORMAT;
    public static final String ORACLE_DATE_TIME_FORMAT = MYSQL_DATE_FORMAT + " " + MYSQL_TIME_FORMAT;
    public static final String SIMPLE_DATE_FORMAT = "ddMMyyyy";
    public static final String SIMPLE_TIME_FORMAT = "HHmmss";
    public static final String SIMPLE_DATETIME_FORMAT = SIMPLE_DATE_FORMAT + " " + SIMPLE_TIME_FORMAT;

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
        if (ObjectHelper.objectIsNull(localDateTime)) {
            localDateTime = LocalDateTime.now();
        }
        return localDateTime.format(DateTimeFormatter.ofPattern("HHmmss|ddMMyyyy"));
    }

    public static String convertLocalDateToString(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static String convertLocalTimeToString(LocalTime localTime, String format) {
        return localTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String convertLocalDateTimeToString(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String localDateMySqlFormat(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(MYSQL_DATE_FORMAT));
    }

    public static String localTimeMySqlFormat(LocalTime localTime) {
        return localTime.format(DateTimeFormatter.ofPattern(MYSQL_TIME_FORMAT));
    }

    public static String localDateTimeMySqlFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(MYSQL_DATE_TIME_FORMAT));
    }

    public static LocalDateTime parseLocalDateTimeMySqlFormat(String mysqlLocalDateTime) {
        return LocalDateTime.parse(mysqlLocalDateTime, DateTimeFormatter.ofPattern(MYSQL_DATE_TIME_FORMAT));
    }

    public static LocalDateTime convertDateToLocalDateTime(Date in) {
        return LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
    }

    /*
    0123456789
    10.10.2010
    * */
    public static LocalDate getLocalDate(String value) {
        if (ObjectHelper.objectIsNotNull(value) && value.length() == 10) {
            return LocalDate.of(Integer.valueOf(value.substring(6)), Integer.valueOf(value.substring(3, 5))
                    , Integer.valueOf(value.substring(0, 2)));
        }
        return null;
    }

    /*
    0123456789
    02:15:09
    * */
    public static LocalTime getLocalTime(String value) {
        if (ObjectHelper.objectIsNotNull(value) && value.length() == 8) {
            return LocalTime.of(Integer.valueOf(value.substring(0, 2)), Integer.valueOf(value.substring(3, 5))
                    , Integer.valueOf(value.substring(6, 8)));
        }
        return null;
    }

    /*
    0123456789012345678
    02:15:09 15.10.2010
    * */
    public static LocalDateTime getLocalDateTime(String value) {
        if (ObjectHelper.objectIsNotNull(value) && value.length() == 19) {
            return LocalDateTime.of(getLocalDate(value.substring(9, 19)), getLocalTime(value.substring(0, 8)));
        }
        return null;
    }

    public static Date convertCurrentLocalDateTimeToDate() {
        return Date.from(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}