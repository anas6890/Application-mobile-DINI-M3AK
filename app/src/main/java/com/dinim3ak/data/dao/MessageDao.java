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
}

