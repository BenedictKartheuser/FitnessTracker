package com.example.fitnesstracker;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Profile {

    public static final String DEFAULT_NAME = "User";
    public static final int DEFAULT_SIZE = 180;
    public static final int DEFAULT_WEIGHT = 70;

    @PrimaryKey
    private String name;
    private int size;
    private int weight;

    public Profile(String name, int size, int weight) {
        setName(name);
        setSize(size);
        setWeight(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
