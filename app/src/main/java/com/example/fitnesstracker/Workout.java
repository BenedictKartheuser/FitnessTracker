package com.example.fitnesstracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity
public class Workout {

    @PrimaryKey
    private Timestamp timestamp;
    private Sport sport;
    private int duration;
    private Profile profile;
    private int calorieConsumption;

    public Workout(Timestamp timestamp, Sport sport, int duration, Profile profile) {
        this.timestamp = timestamp;
        this.sport = sport;
        this.duration = duration;
        this.profile = profile;
        this.calorieConsumption = getCalorieConsumption();
    }

    private int getCalorieConsumption() {
        return (int)(profile.getWeight() * sport.getFactor() * duration);
    }
}
