package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Workout {

    @PrimaryKey @NonNull
    private String timestamp;

    @ColumnInfo(name = "sport")
    private String sport;

    @ColumnInfo (name = "duration")
    private int duration;

    @ColumnInfo (name = "profile")
    private String profile;

    @ColumnInfo (name = "calorieConsumption")
    private int calorieConsumption;
    private static final int MIN_PER_HOUR = 60;

    public Workout(String sport, int duration, String profile) {
        this.timestamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.sport = sport;
        this.duration = duration;
        this.profile = profile;
        this.calorieConsumption = calculateCalorieConsumption();
    }

    private int calculateCalorieConsumption() {
        int weight = Integer.parseInt(profile.split(",")[2]);
        double factor = Double.parseDouble(sport.split(",")[1]);
        return (int) ((weight * factor * duration) / MIN_PER_HOUR);
    }


    public int calculateCalorieConsumption(Workout workout) {
        int weight = Integer.parseInt(workout.getProfile().split(",")[2]);
        double factor = Double.parseDouble(workout.getSport().split(",")[1]);
        return (int)(weight * factor * workout.getDuration());
    }

    public void setCalorieConsumption(int calorieConsumption) {
        this.calorieConsumption = calorieConsumption;
    }

    @NonNull
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
