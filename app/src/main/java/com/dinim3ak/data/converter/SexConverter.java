package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.Sex;

public class SexConverter {
    @TypeConverter
    public static Sex toSex(String sex) {
        return sex == null ? null : Sex.valueOf(sex);
    }

    @TypeConverter
    public static String fromSex(Sex sex) {
        return sex == null ? null : sex.name();
    }
}
