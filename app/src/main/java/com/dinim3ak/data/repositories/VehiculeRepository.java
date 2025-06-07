package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.VehiculeDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Vehicule;

import java.util.List;
import java.util.concurrent.Executors;
public class VehiculeRepository {
    private VehiculeDao vehiculeDao;

    public VehiculeRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        vehiculeDao = db.vehiculeDao();
    }

    public void insert(Vehicule vehicle) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = vehiculeDao.insert(vehicle);
        vehicle.setId(generatedId);
        });
    }
}