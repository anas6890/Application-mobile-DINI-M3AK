package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.dinim3ak.data.dao.UtilisateurDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Utilisateur;

import java.util.List;
import java.util.concurrent.Executors;

public class UtilisateurRepository {
    private UtilisateurDao utilisateurDao;

    public UtilisateurRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        utilisateurDao = db.utilisateurDao();
    }

    public void insert(Utilisateur utilisateur) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = utilisateurDao.insert(utilisateur);
            utilisateur.setId(generatedId);
        });
    }

    public LiveData<Utilisateur> findByTel(String tel){
        return utilisateurDao.findByTel(tel);
    }
    public LiveData<Utilisateur> findById(long id){
        return utilisateurDao.findById(id);
    }
    public LiveData<Utilisateur> findByEmail(String email){
        return utilisateurDao.findByEmail(email);
    }

    public LiveData<List<Utilisateur>> getAll() {
        return utilisateurDao.getAll();
    }
}
