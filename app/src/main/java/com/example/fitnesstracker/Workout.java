package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Workout with timestamp, sport, duration, profile and calorieConsumption
 * Getter and Setter for parameters
 */
@Entity
public class Workout {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "timestamp")
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

    /**
     * Constructor
     * calorieConsumption to be calculated
     * @param sport sport
     * @param duration duration
     * @param profile profile
     */
    public Workout(String sport, int duration, String profile) {
        this.timestamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.sport = sport;
        this.duration = duration;
        this.profile = profile;
        this.calorieConsumption = calculateCalorieConsumption();
    }

    /**
     * Calculates calorie consumption of a workout with workout data
     * @return calorie consumption of workout
     */
    private int calculateCalorieConsumption() {
        int weight = Integer.parseInt(profile.split(",")[2]);
        double factor = Double.parseDouble(sport.split(",")[1]);
        return (int) ((weight * factor * duration) / MIN_PER_HOUR);
    }

    public void setCalorieConsumption(int calorieConsumption) {
        this.calorieConsumption = calorieConsumption;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
