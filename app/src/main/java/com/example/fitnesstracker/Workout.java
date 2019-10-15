package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity
public class Workout {

    @PrimaryKey @NonNull
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
        this.calorieConsumption = calculateCalorieConsumption();
    }

    private int calculateCalorieConsumption() {
        return (int)(profile.getWeight() * sport.getFactor() * duration);
    }

    public int getDuration() {
        return duration;
    }

    public int getCalorieConsumption() {
        return calorieConsumption;
    }

    @Override @NonNull
    public String toString() {
        return this.timestamp + "," + this.sport + "," + this.duration + "," + this.profile + "," + this.calorieConsumption;
    }
}
