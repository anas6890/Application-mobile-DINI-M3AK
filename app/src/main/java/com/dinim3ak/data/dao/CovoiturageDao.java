package com.dinim3ak.data.dao;

import androidx.room.*;

import com.dinim3ak.model.Covoiturage;

import java.util.List;
@Dao
public interface CovoiturageDao {
    @Insert
    long insert(Covoiturage covoiturage);

    @Update
    void update(Covoiturage covoiturage);

    @Delete
    void delete(Covoiturage covoiturage);

    @Query("SELECT * FROM Covoiturage WHERE id = :id")
    Covoiturage getById(int id);

    @Query("SELECT * FROM Covoiturage")
    List<Covoiturage> getAll();
}
