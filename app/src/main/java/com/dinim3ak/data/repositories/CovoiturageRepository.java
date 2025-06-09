package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.CovoiturageDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Utilisateur;

import java.util.Date;
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
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = covoiturageDao.insert(covoiturage);
        covoiturage.setId(generatedId);
        });
    }

    public List<Covoiturage> getAll() {
        return covoiturageDao.getAll();
    }

    public void update(Covoiturage trip) {
    }

    public Covoiturage findById(long trajetId) {
        return null;
    }

    public List<Covoiturage> searchCovoiturage(String departure, String destination, Date date) {
        return null;
    }

    public List<Covoiturage> getCovoiturageByConducteurId(long id) {
        return null;
    }

    public List<Covoiturage> getCovoiturageReserveByUserId(long id) {
        return null;
    }

    public void delete(long tripId) {
    }

    public List<Utilisateur> getPassagersByCovoiturageId(long tripId) { return null;
    }
}
