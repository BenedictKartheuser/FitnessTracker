package com.example.fitnesstracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.sql.Timestamp;

@Dao
public interface SportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Sport sport);

    @Query("SELECT name FROM Sport")
    public String getName();

    @Query("SELECT factor FROM Sport")
    public double getFactor();

    @Delete
    public void delete(Sport sport);
}
