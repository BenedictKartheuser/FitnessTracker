package com.example.fitnesstracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity
public class Workout {

    @PrimaryKey
    private Timestamp timestamp;
    private Sport sport;
    private String duration;

    public Workout(Timestamp timestamp, Sport sport, String duration) {
        this.timestamp = timestamp;
        this.sport = sport;
        this.duration = duration;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Sport getSport() {
        return sport;
    }

    public String getDuration() {
        return duration;
    }
}
