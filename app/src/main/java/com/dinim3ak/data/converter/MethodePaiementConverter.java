package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;
import com.dinim3ak.model.MethodePaiement;

public class MethodePaiementConverter {
    @TypeConverter
    public static MethodePaiement toEnum(String value) {
        return value == null ? null : MethodePaiement.valueOf(value);
    }

    @TypeConverter
    public static String fromEnum(MethodePaiement value) {
        return value == null ? null : value.name();
    }
}
