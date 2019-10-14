package com.example.fitnesstracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sport {

    @PrimaryKey
    private String name;
    private double factor;

    public void setName(String name, double factor) {
        setName(name);
        setFactor(factor);
    }

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
}
