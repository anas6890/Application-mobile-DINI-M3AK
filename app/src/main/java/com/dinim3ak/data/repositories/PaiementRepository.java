package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.PaiementDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Paiement;

import java.util.List;
import java.util.concurrent.Executors;
public class PaiementRepository {
    private PaiementDao paiementDao;

    public PaiementRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        paiementDao = db.paiementDao();
    }

    public void insert(Paiement paiement) {
        Executors.newSingleThreadExecutor().execute(() -> paiementDao.insert(paiement));
    }

}