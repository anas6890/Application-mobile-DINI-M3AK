package com.dinim3ak.data.converter;

import androidx.room.TypeConverter;

import com.dinim3ak.model.ReservationStatus;

public class ReservationStatusConverter {
    @TypeConverter
    public static ReservationStatus toReservationStatus(String reservationStatus) {
        return reservationStatus == null ? null : ReservationStatus.valueOf(reservationStatus);
    }

    @TypeConverter
    public static String fromReservationStatus(ReservationStatus reservationStatus) {
        return reservationStatus == null ? null : reservationStatus.name();
    }
}
