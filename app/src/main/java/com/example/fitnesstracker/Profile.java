package com.example.fitnesstracker;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Profile {

    public static final String DEFAULT_NAME = "User";
    public static final int DEFAULT_SIZE = 180;
    public static final int DEFAULT_WEIGHT = 70;

    @PrimaryKey @NonNull
    private String name;

    @ColumnInfo (name = "height")
    private int height;

    @ColumnInfo (name = "weight")
    private int weight;

    public Profile(String name, int height, int weight) {
        setName(name);
        setHeight(height);
        setWeight(weight);
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override @NonNull
    public String toString() {
        return this.name + "," + this.getHeight() + "," + this.getWeight();
    }

}
