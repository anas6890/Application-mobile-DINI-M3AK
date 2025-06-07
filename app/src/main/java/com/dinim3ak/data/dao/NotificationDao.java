package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Notification;

import java.util.List;
@Dao
public interface NotificationDao {
    @Insert
    long insert(Notification notification);

    @Update
    void update(Notification notification);

    @Delete
    void delete(Notification notification);

    @Query("SELECT * FROM Notification WHERE id = :id")
    Notification getById(int id);
}

