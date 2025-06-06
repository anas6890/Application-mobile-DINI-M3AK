package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Notification {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int utilisateurId;
    public String contenu;
    public TypeNotification type;
    @TypeConverters(DateConverter.class)
    public Date dateEnvoi;
    public boolean lu;

    public void marquerCommeLue() { this.lu = true; }
}