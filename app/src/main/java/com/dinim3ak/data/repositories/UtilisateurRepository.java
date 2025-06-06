package com.dinim3ak.data.repositories;

import android.content.Context;

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
        Executors.newSingleThreadExecutor().execute(() -> utilisateurDao.insert(utilisateur));
    }

    public List<Utilisateur> getAll() {
        return utilisateurDao.getAll();
    }
}
