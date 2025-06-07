package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.WalletDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Wallet;

import java.util.List;
import java.util.concurrent.Executors;
public class WalletRepository {
    private WalletDao walletDao;

    public WalletRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        walletDao = db.walletDao();
    }

    public void insert(Wallet wallet) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = walletDao.insert(wallet);
        wallet.setId(generatedId);
        });
    }

}