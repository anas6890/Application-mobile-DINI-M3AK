package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.PaiementStatus;

public class PaiementStatusConverter {
    @TypeConverter
    public static PaiementStatus toPaiementStatus(String paiementStatus) {
        return paiementStatus == null ? null : PaiementStatus.valueOf(paiementStatus);
    }

    @TypeConverter
    public static String fromPaiementStatus(PaiementStatus paiementStatus) {
        return paiementStatus == null ? null : paiementStatus.name();
    }
}
