package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Vehicule;

import java.util.List;
@Dao
public interface VehiculeDao {
    @Insert
    long insert(Vehicule vehicule);

    @Update
    void update(Vehicule vehicule);

    @Delete
    void delete(Vehicule vehicule);

    @Query("SELECT * FROM Vehicule WHERE id = :id")
    Vehicule getById(int id);
}
