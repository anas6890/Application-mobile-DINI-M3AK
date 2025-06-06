package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.Sex;

public class SexConverter {
    @TypeConverter
    public static Sex toRole(String sex) {
        return sex == null ? null : Sex.valueOf(sex);
    }

    @TypeConverter
    public static String fromRole(Sex sex) {
        return sex == null ? null : sex.name();
    }
}
