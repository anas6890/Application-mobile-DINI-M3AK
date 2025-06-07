package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.MessageDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Message;

import java.util.List;
import java.util.concurrent.Executors;
public class MessageRepository {
    private MessageDao messageDao;

    public MessageRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        messageDao = db.messageDao();
    }

    public void insert(Message message) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = messageDao.insert(message);
        message.setId(generatedId);
        });
    }


}