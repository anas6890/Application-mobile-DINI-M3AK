package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity
public class Commentaire {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int auteurId;
    public int covoiturageId;
    public String texte;
    @TypeConverters(DateConverter.class)
    public Date date;
}
