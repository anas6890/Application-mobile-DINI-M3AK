package com.dinim3ak.data.dao;

import androidx.room.*;

import com.dinim3ak.model.Utilisateur;

import java.util.List;

@Dao
public interface UtilisateurDao {
    @Insert
    default long insert(Utilisateur utilisateur) {
    }

    @Update
    void update(Utilisateur utilisateur);

    @Delete
    void delete(Utilisateur utilisateur);

    @Query("SELECT * FROM Utilisateur WHERE id = :id")
    Utilisateur getById(int id);

    @Query("SELECT * FROM Utilisateur WHERE email = :email")
    Utilisateur getByEmail(String email);

    @Query("SELECT * FROM Utilisateur")
    List<Utilisateur> getAll();
}
