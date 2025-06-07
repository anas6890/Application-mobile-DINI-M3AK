package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.PaiementStatus;

public class PaiementStatusConverter {
    @TypeConverter
    public static String fromPaiementStatus(PaiementStatus paiementStatus) {
        return paiementStatus == null ? null : paiementStatus.name();
    }

    @TypeConverter
    public static PaiementStatus toPaiementStatus(String paiementStatus) {
        if (paiementStatus == null || paiementStatus.isEmpty()) {
            return null;
        }
        try {
            return PaiementStatus.valueOf(paiementStatus);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid PaiementStatus string from DB: " + paiementStatus + ". Error: " + e.getMessage());
            return null;
        }
    }
}
