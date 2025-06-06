package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public float montant;
    public String description;
    @TypeConverters(DateConverter.class)
    public Date date;
}