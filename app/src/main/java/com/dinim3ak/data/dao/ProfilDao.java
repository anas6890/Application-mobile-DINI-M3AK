package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Profil;

import java.util.List;
@Dao
public interface ProfilDao {
    @Insert
    void insert(Profil profil);

    @Update
    void update(Profil profil);

    @Delete
    void delete(Profil profil);

    @Query("SELECT * FROM Profil WHERE id = :id")
    Profil getById(int id);
}
