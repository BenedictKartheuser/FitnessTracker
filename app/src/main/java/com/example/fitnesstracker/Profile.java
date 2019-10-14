package com.example.fitnesstracker;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Profile {

    public static final String DEFAULT_NAME = "User";
    public static final int DEFAULT_SIZE = 180;
    public static final int DEFAULT_WEIGTH = 70;

    @PrimaryKey
    private String name;
    private int size;
    private int weigth;

    public Profile(String name, int size, int weigth) {
        setName(name);
        setSize(size);
        setWeigth(weigth);
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
