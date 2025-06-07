package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Message;

import java.util.List;
@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Update
    void update(Message message);

    @Delete
    void delete(Message message);

    @Query("SELECT * FROM Message WHERE id = :id")
    Message getById(int id);
    @Query("SELECT * FROM Message WHERE trajetId = :trajetId ORDER BY dateEnvoi ASC")
    List<Message> getMessagesForTrajet(int trajetId);

    @Query("SELECT * FROM Message WHERE (expediteurId = :userId OR destinataireId = :userId) ORDER BY dateEnvoi DESC")
    List<Message> getMessagesForUser(int userId);

}

