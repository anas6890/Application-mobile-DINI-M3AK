package com.dinim3ak.data.dao;
import androidx.room.*;

import com.dinim3ak.model.Wallet;

import java.util.List;
@Dao
public interface WalletDao {
    @Insert
    void insert(Wallet wallet);

    @Update
    void update(Wallet wallet);

    @Delete
    void delete(Wallet wallet);

    @Query("SELECT * FROM Wallet WHERE id = :id")
    Wallet getById(int id);
}
