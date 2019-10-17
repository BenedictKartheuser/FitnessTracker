package com.example.fitnesstracker.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.fitnesstracker.MainActivity;
import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.R;

import static androidx.core.content.ContextCompat.getSystemService;


public class Dashboard extends Fragment {

    private Profile profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setUp();

        //Activates the OnFocusChangeListener on the height & weight edit fields to hide the keyboard and save the changes.
        changeOnState();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }


    public void setUp() {
        //Hier müsste profile aus der datenbank geladen werden
        //profile = DAO;
        if (profile == null) {
            profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_SIZE, Profile.DEFAULT_WEIGHT);
        }
        setContents();
    }

    private void setContents() {
        //Hier werden die EditText Felder mit den Infos aus dem Profil befüllt
        //... = profile.getName();
        //... = profile.getSize();
        //... = profile.getWeight();
    }



    public void changeOnState(){
        EditText weightText = findViewById(R.id.weight);
        EditText heightText = findViewById(R.id.height);
        EditText helloText = findViewById(R.id.name_edit);
        final MainActivity a = new MainActivity();

        weightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    a.hideKeyboard(view);
                }
            }
        });

        heightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    a.hideKeyboard(view);
                }
            }
        });

        helloText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    a.hideKeyboard(view);
                }
            }
        });
    }
}

