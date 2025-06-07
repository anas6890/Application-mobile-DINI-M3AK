package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;
import com.dinim3ak.model.TypeNotification;

public class TypeNotificationConverter {
    @TypeConverter
    public static TypeNotification toEnum(String value) {
        return value == null ? null : TypeNotification.valueOf(value);
    }

    @TypeConverter
    public static String fromEnum(TypeNotification value) {
        return value == null ? null : value.name();
    }
}
