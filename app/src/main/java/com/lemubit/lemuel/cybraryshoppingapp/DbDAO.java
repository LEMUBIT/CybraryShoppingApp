package com.lemubit.lemuel.cybraryshoppingapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbDAO {
    @Insert
    void insertItems(DbTable... dbTables);

    @Query("SELECT * from DbTable")
    LiveData<List<DbTable>> getAllItems();

    @Query("SELECT COUNT(*) from DbTable")
    LiveData<Integer> countItems();

    @Delete
    void deleteItem(DbTable dbTable);
}
