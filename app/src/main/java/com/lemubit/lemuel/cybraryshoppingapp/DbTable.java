package com.lemubit.lemuel.cybraryshoppingapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DbTable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String item;

    public int quantity;
}
