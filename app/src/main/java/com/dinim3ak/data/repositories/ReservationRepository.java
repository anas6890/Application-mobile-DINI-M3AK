package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
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

    public LiveData<List<Reservation>> getReservationsByUserId(long id) {
        return reservationDao.getReservationsByUserID(id);
    }

    public void update(Reservation reservation) {
        Executors.newSingleThreadExecutor().execute(()->{
            reservationDao.update(reservation);
        });
    }

    public LiveData<Reservation> findById(long reservationId) {
        return reservationDao.findById(reservationId);
    }

    public LiveData<List<Reservation>> getReservationsByCovoiturageId(long tripId) {
        return reservationDao.getReservationsByCovoiturageId(tripId);
    }

    public void delete(Reservation reservation) {
        Executors.newSingleThreadExecutor().execute(()->{
            reservationDao.delete(reservation);
        });
    }
}
