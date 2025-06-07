package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;
import com.dinim3ak.model.TypeNotification;

public class TypeNotificationConverter {

    @TypeConverter
    public static TypeNotification toTypeNotification(String typeNotification) {
        return typeNotification == null ? null : TypeNotification.valueOf(typeNotification);
    }

    @TypeConverter
    public static String fromTypeNotification(TypeNotification typeNotification) {
        return typeNotification == null ? null : typeNotification.name();
    }
}
