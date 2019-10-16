package com.example.fitnesstracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.sql.Timestamp;

@Dao
public interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Workout workout);

    @Query("SELECT * FROM Workout")
    public Workout getWorkout();

    @Query("SELECT sport FROM Workout")
    public String getSport();

    @Query("SELECT timestamp FROM Workout")
    public String getTimestamp();

    @Query("SELECT duration FROM Workout")
    public int getDuration();

    @Query("SELECT calorieConsumption FROM Workout")
    public int getCalorieConsumption();

    @Delete
    public void delete(Workout workout);
}
