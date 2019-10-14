package com.example.fitnesstracker;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Profile {

    @PrimaryKey
    private String name;
    private int size;
    private int weigth;

    public Profile() {

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

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

}
