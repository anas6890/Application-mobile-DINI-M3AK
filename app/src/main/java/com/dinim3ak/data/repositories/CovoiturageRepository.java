package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.dinim3ak.data.dao.CovoiturageDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Covoiturage;
import com.dinim3ak.model.Utilisateur;

import java.time.LocalDate;
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

    public void delete(Covoiturage covoiturage) {
        Executors.newSingleThreadExecutor().execute(() -> {
            covoiturageDao.delete(covoiturage);
        });
    }

    public LiveData<List<Covoiturage>> getAll() {
        return covoiturageDao.getAll();
    }

    public void update(Covoiturage trip) {
        Executors.newSingleThreadExecutor().execute(()->{
            covoiturageDao.update(trip);
        });
    }

    public LiveData<Covoiturage> findById(long trajetId) {
        return covoiturageDao.getById(trajetId);
    }

    public LiveData<List<Covoiturage>> searchCovoiturage(String departure, String destination, LocalDate date) {
        return covoiturageDao.searchCovoiturage(departure, destination, date);
    }

    public LiveData<List<Covoiturage>> getCovoiturageByConducteurId(long id) {
        return covoiturageDao.getCovoiturageByConducteurId(id);
    }

    public LiveData<List<Covoiturage>> getCovoiturageReserveByUserId(long id) {
        return null;
    }

    public void delete(long tripId) {
    }

    public List<Utilisateur> getPassagersByCovoiturageId(long tripId) { return null;
    }

    public LiveData<List<Covoiturage>> getCovoituragesByIds(List<Long> covoiturageIds) {
        return covoiturageDao.getCovoituragesByIds(covoiturageIds);
    }
}
