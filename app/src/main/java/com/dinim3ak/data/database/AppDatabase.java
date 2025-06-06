package com.dinim3ak.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dinim3ak.data.converter.*;
import com.dinim3ak.data.dao.*;
import com.dinim3ak.model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Utilisateur.class,
        Covoiturage.class,
        Vehicule.class,
        Reservation.class,
        Notification.class,
        Message.class,
        Paiement.class,
        Evaluation.class,
        Wallet.class,
        Profil.class
}, version = 1)
@TypeConverters({SexConverter.class, ReservationStatusConverter.class, PaiementStatusConverter.class, DateConverter.class, StringListConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UtilisateurDao utilisateurDao();
    public abstract CovoiturageDao covoiturageDao();
    public abstract VehiculeDao vehiculeDao();
    public abstract ReservationDao reservationDao();
    public abstract NotificationDao notificationDao();
    public abstract MessageDao messageDao();
    public abstract PaiementDao paiementDao();
    public abstract EvaluationDao evaluationDao();
    public abstract WalletDao walletDao();
    public abstract ProfilDao profilDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
}
