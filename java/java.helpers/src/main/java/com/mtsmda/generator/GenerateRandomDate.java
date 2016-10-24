package com.mtsmda.generator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by MTSMDA on 20.10.2016.
 */
public class GenerateRandomDate {

    public static LocalDateTime getRandomLocalDateTime(int yearBegin, int yearEnd) {
        GenerateRandom generateRandom = new GenerateRandom(GenerateRandom.ONLY_NUMBERS);
        LocalDateTime localDateTime = null;
        boolean isFalse = false;
        do {
            try {
                int year = GenerateRandom.generateRandomNumberInRange(yearBegin, yearEnd);
                int month = GenerateRandom.generateRandomNumberInRange(1, 12);
                int dayOfMonth = GenerateRandom.generateRandomNumberInRange(1, 31);

                int hour = GenerateRandom.generateRandomNumberInRange(1, 23);
                int minute = GenerateRandom.generateRandomNumberInRange(1, 59);
                int second = GenerateRandom.generateRandomNumberInRange(1, 59);
                localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
                isFalse = false;
            } catch (RuntimeException e) {
                isFalse = true;
            }
        } while (isFalse);

        return localDateTime;
    }

    public static LocalDate getRandomLocalDate(int yearBegin, int yearEnd) {
        return getRandomLocalDateTime(yearBegin, yearEnd).toLocalDate();
    }

    public static LocalTime getRandomLocalTime(int yearBegin, int yearEnd) {
        return getRandomLocalDateTime(yearBegin, yearEnd).toLocalTime();
    }

    public static Date getRandomDate(int yearBegin, int yearEnd) {
        return Date.from(getRandomLocalDate(yearBegin, yearEnd).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}