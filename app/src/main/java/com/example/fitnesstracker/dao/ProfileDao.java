package com.example.fitnesstracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fitnesstracker.Profile;

import java.util.List;

@Dao
public interface ProfileDao {

    @Query("UPDATE Profile SET name = :name_")
    void updateName(String name_);

    @Query("UPDATE Profile SET name = :height_")
    void updateHeight(int height_);

    @Query("UPDATE Profile SET name = :weigtht_")
    void updateWeight(int weigtht_);

    @Query("SELECT * FROM Profile")
    public Profile getProfile();

    @Query("SELECT name FROM Profile")
    public String getName();

    @Query("SELECT height FROM Profile")
    public int getSize();

    @Query("SELECT weight FROM Profile")
    public int getWeight();

    @Delete
    public void delete(Profile profile);

}
