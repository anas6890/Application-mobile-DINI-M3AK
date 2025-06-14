package com.dinim3ak.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.dinim3ak.model.Covoiturage;

import java.time.LocalDate;
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
    LiveData<Covoiturage> getById(long id);

    @Query("SELECT * FROM Covoiturage")
    LiveData<List<Covoiturage>> getAll();

    @Query("SELECT * FROM Covoiturage WHERE (:departure = '' OR :departure IS NULL OR villeDepart = :departure) AND " +
            "(:destination = '' OR :destination IS NULL OR villeArrivee = :destination) AND " +
            "(:date IS NULL OR dateDepart = :date)")
    LiveData<List<Covoiturage>> searchCovoiturage(String departure, String destination, LocalDate date);

    @Query("SELECT * FROM Covoiturage WHERE conducteurId = :id")
    LiveData<List<Covoiturage>> getCovoiturageByConducteurId(long id);

    @Query("SELECT * FROM Covoiturage WHERE id IN (:covoiturageIds)")
    LiveData<List<Covoiturage>> getCovoituragesByIds(List<Long> covoiturageIds);
}
