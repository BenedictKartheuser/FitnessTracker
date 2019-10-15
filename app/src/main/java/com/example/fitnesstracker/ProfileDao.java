package com.example.fitnesstracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfileDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Profile profile);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSize(int size);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWeight(int weight);

    @Query("SELECT name FROM Profile")
    public List<Profile> getName();

    @Query("SELECT size FROM Profile")
    public List<Profile> getSize();

    @Query("SELECT weigth FROM Profile")
    public List<Profile> getWeight();

    @Delete
    public void delete(Profile profile);

}
