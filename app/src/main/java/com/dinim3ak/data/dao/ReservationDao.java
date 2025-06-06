package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Reservation;

import java.util.List;
@Dao
public interface ReservationDao {
    @Insert
    void insert(Reservation reservation);

    @Update
    void update(Reservation reservation);

    @Delete
    void delete(Reservation reservation);

    @Query("SELECT * FROM Reservation WHERE id = :id")
    Reservation getById(int id);
}
