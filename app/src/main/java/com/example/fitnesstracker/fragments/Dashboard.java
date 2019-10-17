package com.example.fitnesstracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.R;


public class Dashboard extends Fragment {

    private Profile profile;
    private int lastWeek;


    public void setUp() {
        //Hier müsste profile aus der datenbank geladen werden
        //profile = DAO;
        if (profile == null) {
            profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_SIZE, Profile.DEFAULT_WEIGHT);
        }
        setContents();

        //nicht new History eigentlich, sondern History aus DB laden
        History history = new History();
        lastWeek = history.getLastWeekCalories(history.getHistory());
    }

    private void setContents() {
        //Hier werden die EditText Felder mit den Infos aus dem Profil befüllt
        //... = profile.getName();
        //... = profile.getSize();
        //... = profile.getWeight();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setUp();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }


}

