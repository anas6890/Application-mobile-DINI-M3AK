package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;
import com.dinim3ak.model.MethodePaiement;

public class MethodePaiementConverter {

    @TypeConverter
    public static MethodePaiement toMethodePaiement(String methodePaiement) {
        return methodePaiement == null ? null : MethodePaiement.valueOf(methodePaiement);
    }

    @TypeConverter
    public static String fromMethodePaiement(MethodePaiement methodePaiement) {
        return methodePaiement == null ? null : methodePaiement.name();
    }
}
