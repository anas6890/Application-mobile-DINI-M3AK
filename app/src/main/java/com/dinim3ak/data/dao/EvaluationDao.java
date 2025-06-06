package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Evaluation;

import java.util.List;
@Dao
public interface EvaluationDao {
    @Insert
    void insert(Evaluation evaluation);

    @Update
    void update(Evaluation evaluation);

    @Delete
    void delete(Evaluation evaluation);

    @Query("SELECT * FROM Evaluation WHERE id = :id")
    Evaluation getById(int id);
}
