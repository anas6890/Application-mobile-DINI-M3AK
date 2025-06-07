package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.CovoiturageStatus;
import com.dinim3ak.model.TypeCovoiturage;

public class CovoiturageConverter {

    @TypeConverter
    public static CovoiturageStatus toStatut(String statut) {
        return statut == null ? null : CovoiturageStatus.valueOf(statut);
    }

    @TypeConverter
    public static String fromStatut(CovoiturageStatus statut) {
        return statut == null ? null : statut.name();
    }

    @TypeConverter
    public static TypeCovoiturage toType(String type) {
        return type == null ? null : TypeCovoiturage.valueOf(type);
    }

    @TypeConverter
    public static String fromType(TypeCovoiturage type) {
        return type == null ? null : type.name();
    }
}
