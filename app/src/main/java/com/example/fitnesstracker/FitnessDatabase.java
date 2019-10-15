package com.example.fitnesstracker;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Profile.class, Sport.class, Workout.class}, version = 1, exportSchema = false)
public abstract class FitnessDatabase extends RoomDatabase {

    public abstract ProfileDao profileDao();
    public abstract SportDao sportDao();
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
