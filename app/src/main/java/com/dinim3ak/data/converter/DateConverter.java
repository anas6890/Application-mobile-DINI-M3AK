package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;  // yyyy-MM-dd
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;  // HH:mm:ss

    @TypeConverter
    public static String localDateToString(LocalDate date) {
        return date == null ? null : date.format(DATE_FORMATTER);
    }

    @TypeConverter
    public static LocalDate stringToLocalDate(String value) {
        return value == null ? null : LocalDate.parse(value, DATE_FORMATTER);
    }

    @TypeConverter
    public static String localTimeToString(LocalTime time) {
        return time == null ? null : time.format(TIME_FORMATTER);
    }

    @TypeConverter
    public static LocalTime stringToLocalTime(String value) {
        return value == null ? null : LocalTime.parse(value, TIME_FORMATTER);
    }
}
