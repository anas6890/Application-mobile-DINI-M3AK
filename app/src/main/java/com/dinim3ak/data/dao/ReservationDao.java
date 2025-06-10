package com.dinim3ak.data.dao;
import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.dinim3ak.model.Reservation;

@Dao
public interface ReservationDao {
    @Insert
    long insert(Reservation reservation);

    @Update
    void update(Reservation reservation);

    @Delete
    void delete(Reservation reservation);

    @Query("SELECT * FROM Reservation WHERE id = :id")
    LiveData<Reservation> findById(long id);
}
