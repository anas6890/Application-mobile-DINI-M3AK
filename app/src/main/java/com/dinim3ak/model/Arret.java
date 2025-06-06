package com.dinim3ak.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Arret {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String ville;
    public int covoiturageId;
}