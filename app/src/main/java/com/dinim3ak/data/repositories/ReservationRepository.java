package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.ReservationDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Reservation;

import java.util.List;
import java.util.concurrent.Executors;
public class ReservationRepository {
    private ReservationDao reservationDao;

    public ReservationRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        reservationDao = db.reservationDao();
    }

    public void insert(Reservation reservation) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = reservationDao.insert(reservation);
        reservation.setId(generatedId);
        });
    }

    public List<Reservation> getReservationsByUserId(long id) {
        return java.util.Collections.emptyList();
    }

    public void update(Reservation reservation) {
    }

    public Reservation findById(long reservationId) {
        return null;
    }

    public List<Reservation> getReservationsByCovoiturageId(long tripId) {
        return java.util.Collections.emptyList();
    }

    public void delete(long reservationId) {
    }
}
