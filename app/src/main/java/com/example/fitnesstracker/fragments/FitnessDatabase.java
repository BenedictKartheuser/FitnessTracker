package com.example.fitnesstracker.fragments;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.Sport;
import com.example.fitnesstracker.Workout;
import com.example.fitnesstracker.dao.ProfileDao;
import com.example.fitnesstracker.dao.WorkoutDao;

@Database(entities = {Profile.class, Sport.class, Workout.class}, version = 2, exportSchema = false)
public abstract class FitnessDatabase extends RoomDatabase {

    public abstract ProfileDao profileDao();
    public abstract WorkoutDao workoutDao();

    private static FitnessDatabase INSTANCE;

    static FitnessDatabase getDatabase(Context context){


        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    FitnessDatabase.class, "fitness_database").build();
        }
        return INSTANCE;
    }

}
