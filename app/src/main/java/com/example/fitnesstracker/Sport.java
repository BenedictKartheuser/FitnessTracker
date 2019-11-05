package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Sport with name and factor
 * Getter and Setter for parameters
 */
@Entity
public class Sport {

    @PrimaryKey @NonNull
    private String name;

    @ColumnInfo(name = "factor")
    private double factor;

    /**
     * Constructor via String
     * @param sport Sport as String
     */
    public Sport (String sport) {
        setName(sport.split(",")[0]);
        setFactor(Double.parseDouble(sport.split(",")[1]));
    }

    public Sport (String name, Double factor) {
        this.name = name;
        this.factor = factor;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    @Override @NonNull
    public String toString() {
        return this.name + "," + this.getFactor();
    }
}
