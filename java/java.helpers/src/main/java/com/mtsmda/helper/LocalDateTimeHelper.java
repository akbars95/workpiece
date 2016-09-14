package com.mtsmda.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by dminzat on 8/12/2016.
 */
public class LocalDateTimeHelper {

    public static final String MYSQL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MYSQL_TIME_FORMAT = "HH:mm:ss";
    public static final String MYSQL_DATE_TIME_FORMAT = MYSQL_DATE_FORMAT + " " + MYSQL_TIME_FORMAT;

    public static String localDate(LocalDate localDate, String format) {
        return localDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static String localTime(LocalTime localTime, String format) {
        return localTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String localDateTime(LocalDateTime localDateTime, String format) {
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

}