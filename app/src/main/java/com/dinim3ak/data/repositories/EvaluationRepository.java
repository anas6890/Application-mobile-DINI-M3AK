package com.dinim3ak.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.dinim3ak.data.dao.EvaluationDao;
import com.dinim3ak.data.database.AppDatabase;
import com.dinim3ak.model.Evaluation;

import java.util.List;
import java.util.concurrent.Executors;
public class EvaluationRepository {
    private EvaluationDao evaluationDao;

    public EvaluationRepository(Context context) {
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "my-database").build();
        evaluationDao = db.evaluationDao();
    }

    public void insert(Evaluation evaluation) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long generatedId = evaluationDao.insert(evaluation);
        evaluation.setId(generatedId);
        });
    }

}
