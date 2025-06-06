package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.ProfilDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Profil;

import java.util.List;
import java.util.concurrent.Executors;
// ProfilRepository.java
public class ProfilRepository {
    private ProfilDao profilDao;

    public ProfilRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        profilDao = db.profilDao();
    }

    public void insert(Profil profil) {
        Executors.newSingleThreadExecutor().execute(() -> profilDao.insert(profil));
    }

}
