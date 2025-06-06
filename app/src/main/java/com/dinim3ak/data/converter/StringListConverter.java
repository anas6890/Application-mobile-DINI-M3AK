package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class StringListConverter {
    @TypeConverter
    public static List<String> fromString(String value) {
        return value == null || value.isEmpty()
                ? new ArrayList<>()
                : Arrays.asList(value.split(","));
    }

    @TypeConverter
    public static String fromList(List<String> list) {
        return list == null ? "" : String.join(",", list);
    }
}

