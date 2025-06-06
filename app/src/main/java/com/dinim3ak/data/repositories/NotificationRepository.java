package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.NotificationDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Notification;

import java.util.List;
import java.util.concurrent.Executors;
public class NotificationRepository {
    private NotificationDao notificationDao;

    public NotificationRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        notificationDao = db.notificationDao();
    }

    public void insert(Notification notification) {
        Executors.newSingleThreadExecutor().execute(() -> notificationDao.insert(notification));
    }

}