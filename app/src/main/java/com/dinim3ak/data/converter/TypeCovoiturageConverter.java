package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.TypeCovoiturage;

public class TypeCovoiturageConverter {
    @TypeConverter
    public static TypeCovoiturage toTypeCovoiturage(String typeCovoiturage) {
        return typeCovoiturage == null ? null : TypeCovoiturage.valueOf(typeCovoiturage);
    }

    @TypeConverter
    public static String fromTypeCovoiturage(TypeCovoiturage typeCovoiturage) {
        return typeCovoiturage == null ? null : typeCovoiturage.name();
    }
}