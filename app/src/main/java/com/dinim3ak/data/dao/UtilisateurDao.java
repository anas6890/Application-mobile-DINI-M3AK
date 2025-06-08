package com.dinim3ak.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.dinim3ak.model.Utilisateur;

import java.util.List;

@Dao
public interface UtilisateurDao {
    @Insert
    long insert(Utilisateur utilisateur);

    @Update
    void update(Utilisateur utilisateur);

    @Delete
    void delete(Utilisateur utilisateur);

    @Query("SELECT * FROM Utilisateur WHERE id = :id")
    LiveData<Utilisateur> findById(long id);

    @Query("SELECT * FROM Utilisateur WHERE email = :email")
    LiveData<Utilisateur> findByEmail(String email);

    @Query("SELECT * FROM Utilisateur")
    LiveData<List<Utilisateur>> getAll();

    @Query("SELECT * FROM Utilisateur WHERE numeroTelephone = :tel")
    LiveData<Utilisateur> findByTel(String tel);
}
