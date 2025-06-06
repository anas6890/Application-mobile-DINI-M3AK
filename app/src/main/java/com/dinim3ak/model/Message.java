package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int expediteurId;
    public int destinataireId;
    public int trajetId;
    public String contenu;
    @TypeConverters(DateConverter.class)
    public Date dateEnvoi;

    public void envoyer() {}
}