package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sport {

    @PrimaryKey @NonNull
    private String name;

    @ColumnInfo(name = "factor")
    private double factor;

    //refactorn
    public Sport (String sport) {
        setName(sport.split(",")[0]);
        setFactor(Double.parseDouble(sport.split(",")[1]));
    }

    public Sport(String name, double factor) {
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

    private void setFactor(double factor) {
        this.factor = factor;
    }

    @Override @NonNull
    public String toString() {
        return this.name + "," + this.getFactor();
    }
}
