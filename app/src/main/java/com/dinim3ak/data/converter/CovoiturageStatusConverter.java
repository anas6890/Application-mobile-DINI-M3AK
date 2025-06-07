package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.CovoiturageStatus;

public class CovoiturageStatusConverter {
    @TypeConverter
    public static CovoiturageStatus toCovoiturageStatus(String covoiturageStatus) {
        return covoiturageStatus == null ? null : CovoiturageStatus.valueOf(covoiturageStatus);
    }

    @TypeConverter
    public static String fromCovoiturageStatus(CovoiturageStatus covoiturageStatus) {
        return covoiturageStatus == null ? null : covoiturageStatus.name();
    }
}