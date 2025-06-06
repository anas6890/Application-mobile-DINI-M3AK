package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Paiement;

import java.util.List;
@Dao
public interface PaiementDao {
    @Insert
    void insert(Paiement paiement);

    @Update
    void update(Paiement paiement);

    @Delete
    void delete(Paiement paiement);

    @Query("SELECT * FROM Paiement WHERE id = :id")
    Paiement getById(int id);
}

