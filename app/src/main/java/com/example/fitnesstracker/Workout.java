package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Workout {

    @PrimaryKey @NonNull
    private String timestamp;
    private String sport;
    private int duration;
    private String profile;
    private int calorieConsumption;

    public Workout(String sport, int duration) {
        this.timestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        this.sport = sport;
        this.duration = duration;
        //Profile aus db laden!
        this.profile = profile;
        this.calorieConsumption = calculateCalorieConsumption();
    }

    private int calculateCalorieConsumption() {
        int weight = Integer.parseInt(profile.split(",")[2]);
        int factor = Integer.parseInt(sport.split(",")[1]);
        return (int)(weight * factor * duration);
    }


    public int calculateCalorieConsumption(Workout workout) {
        int weight = Integer.parseInt(workout.getProfile().split(",")[2]);
        int factor = Integer.parseInt(workout.getSport().split(",")[1]);
        return (int)(weight * factor * workout.getDuration());
    }

    public void setCalorieConsumption(int calorieConsumption) {
        this.calorieConsumption = calorieConsumption;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSport() {
        return sport;
    }

    public int getDuration() {
        return duration;
    }


    public String getProfile() {
        return profile;
    }

    public int getCalorieConsumption() {
        return calorieConsumption;
    }

    @Override @NonNull
    public String toString() {
        return this.timestamp + "," + this.sport + "," + this.duration + "," + this.profile + "," + this.calorieConsumption;
    }


}
