package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.CovoiturageDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Covoiturage;

import java.util.List;
import java.util.concurrent.Executors;
public class CovoiturageRepository {
    private CovoiturageDao covoiturageDao;

    public CovoiturageRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        covoiturageDao = db.covoiturageDao();
    }

    public void insert(Covoiturage covoiturage) {
        Executors.newSingleThreadExecutor().execute(() -> covoiturageDao.insert(covoiturage));
    }

    public List<Covoiturage> getAll() {
        return covoiturageDao.getAll();
    }
}
