package com.mtsmda.validation.structure.validator;

import com.mtsmda.validation.structure.constraint.CheckLocalDateTime;
import com.mtsmda.validation.structure.constraint.DateEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by dminzat on 9/1/2016.
 */
public class CheckLocalDateTimeValidator implements ConstraintValidator<CheckLocalDateTime, String> {

    private DateEnum datePeriod;
    private DateEnum dateType;
    private boolean beginTime;

    @Override
    public void initialize(CheckLocalDateTime constraintAnnotation) {
        this.datePeriod = constraintAnnotation.datePeriod();
        this.dateType = constraintAnnotation.dateType();
        this.beginTime = constraintAnnotation.beginTime();
    }

    /*
    value

LOCAL_DATE
            0123456789(10)
            02.12.2010

LOCAL_TIME
            012345678901(12)
            23:12:02:000

LOCAL_DATE_TIME
        if(beginTime == false)
            01234567890123456789012(23)
            02.12.2010 23:12:02:000

        if(beginTime == true)
            01234567890123456789012(23)
            23:12:02:000 02.12.2010
    * */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean dateTypeResult = false;
        boolean datePeriodResult = false;

        if (dateType == DateEnum.NONE) {
            return true;
        }

        if (datePeriod == DateEnum.NONE) {
            datePeriodResult = true;
        }

        LocalDate localDate = null;
        LocalTime localTime = null;
        LocalDateTime localDateTime = null;

        try {
            switch (dateType) {
                case LOCAL_DATE: {
                    localDate = LocalDate.of(Integer.valueOf(value.substring(6, 10)), Integer.valueOf(value.substring(3, 5)),
                            Integer.valueOf(value.substring(0, 2)));
                    dateTypeResult = true;
                }
                break;
                case LOCAL_TIME: {
                    if (value.length() == 8) {
                        localTime = LocalTime.of(Integer.valueOf(value.substring(0, 2)), Integer.valueOf(value.substring(3, 5)),
                                Integer.valueOf(value.substring(6, 8)));
                    } else {
                        localTime = LocalTime.of(Integer.valueOf(value.substring(0, 2)), Integer.valueOf(value.substring(3, 5)),
                                Integer.valueOf(value.substring(6, 8)), Integer.valueOf(value.substring(9, 12)));
                    }
                    dateTypeResult = true;
                }
                break;
                case LOCAL_DATE_TIME: {
                    if (this.beginTime) {
                        if (value.length() == 19) {
                            localDateTime = LocalDateTime.of(LocalDate.of(Integer.valueOf(value.substring(15, 19)),
                                    Integer.valueOf(value.substring(12, 14)), Integer.valueOf(value.substring(9, 11))),
                                    LocalTime.of(Integer.valueOf(value.substring(0, 2)), Integer.valueOf(value.substring(3, 5)), Integer.valueOf(value.substring(6, 8))));
                        } else {
                            localDateTime = LocalDateTime.of(LocalDate.of(Integer.valueOf(value.substring(19, 23)),
                                    Integer.valueOf(value.substring(16, 18)), Integer.valueOf(value.substring(13, 15))),
                                    LocalTime.of(Integer.valueOf(value.substring(0, 2)),
                                            Integer.valueOf(value.substring(3, 5)),
                                            Integer.valueOf(value.substring(6, 8)),
                                            Integer.valueOf(value.substring(9, 12))));
                        }
                    } else {
                        if (value.length() == 19) {
                            localDateTime = LocalDateTime.of(Integer.valueOf(value.substring(6, 10)), Integer.valueOf(value.substring(3, 5)),
                                    Integer.valueOf(value.substring(0, 2)), Integer.valueOf(value.substring(11, 13)),
                                    Integer.valueOf(value.substring(14, 16)), Integer.valueOf(value.substring(17, 19)));
                        } else {
                            localDateTime = LocalDateTime.of(Integer.valueOf(value.substring(6, 10)), Integer.valueOf(value.substring(3, 5)),
                                    Integer.valueOf(value.substring(0, 2)), Integer.valueOf(value.substring(11, 13)),
                                    Integer.valueOf(value.substring(14, 16)), Integer.valueOf(value.substring(17, 19))
                                    , Integer.valueOf(value.substring(20)));
                        }
                    }

                    dateTypeResult = true;
                }
                break;
            }

            if (dateTypeResult) {
                switch (datePeriod) {
                    case PAST: {
                        switch (dateType) {
                            case LOCAL_DATE: {
                                return localDate.isBefore(LocalDate.now()) && dateTypeResult;
                            }
                            case LOCAL_TIME: {
                                return localTime.isBefore(LocalTime.now()) && dateTypeResult;
                            }
                            case LOCAL_DATE_TIME: {
                                return localDateTime.isBefore(LocalDateTime.now()) && dateTypeResult;
                            }
                        }
                    }
                    break;
                    case FUTURE: {
                        switch (dateType) {
                            case LOCAL_DATE: {
                                return localDate.isAfter(LocalDate.now()) && dateTypeResult;
                            }
                            case LOCAL_TIME: {
                                return localTime.isAfter(LocalTime.now()) && dateTypeResult;
                            }
                            case LOCAL_DATE_TIME: {
                                return localDateTime.isAfter(LocalDateTime.now()) && dateTypeResult;
                            }
                        }
                    }
                    break;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return dateTypeResult && datePeriodResult;
    }

    private boolean isLocalTime(String value) {
        return !(value.length() == 10);
    }

}