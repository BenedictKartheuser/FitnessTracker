package com.example.fitnesstracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fitnesstracker.Profile;

import java.util.List;

/**
 * Profile Dao to handle Profile in Database
 * Needed for Dashboard, personalisation and calculations
 */
@Dao
public interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProfile(Profile profile);

    @Query("UPDATE Profile SET name = :name_")
    void updateName(String name_);

    @Query("UPDATE Profile SET height = :height_")
    void updateHeight(int height_);

    @Query("UPDATE Profile SET weight = :weight_")
    void updateWeight(int weight_);

    @Query("SELECT * FROM Profile")
    public List<Profile> getProfile();

    @Query("SELECT name FROM Profile")
    public String getName();

    @Query("SELECT height FROM Profile")
    public int getSize();

    @Query("SELECT weight FROM Profile")
    public int getWeight();

    @Delete
    public void delete(Profile profile);

}
