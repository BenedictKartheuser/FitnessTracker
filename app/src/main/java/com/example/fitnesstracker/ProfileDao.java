package com.example.fitnesstracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Profile profile);

/*    @Update
    public void updateName(String name);

    @Update
    public void updateSize(int size);

    @Update
    public void updateWeight(int weight);
*/
    @Query("SELECT name FROM Profile")
    public String getName();

    @Query("SELECT size FROM Profile")
    public int getSize();

    @Query("SELECT weight FROM Profile")
    public int getWeight();

    @Delete
    public void delete(Profile profile);

}
