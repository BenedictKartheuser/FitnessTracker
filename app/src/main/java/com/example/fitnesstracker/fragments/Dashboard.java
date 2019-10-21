package com.example.fitnesstracker.fragments;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnesstracker.MainActivity;
import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.R;

import static androidx.core.content.ContextCompat.getSystemService;


public class Dashboard extends Fragment {

    private Profile profile;
    private int lastWeek;




    public void setUp(View view) {
        //Hier müsste profile aus der datenbank geladen werden
        //profile = DAO;
        if (profile == null) {
            profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_SIZE, Profile.DEFAULT_WEIGHT);
        }
        setContents(view);

        //nicht new History eigentlich, sondern History aus DB laden
        History history = new History();
        //lastWeek = history.getLastWeekCalories(history.getHistory());
    }

    private void setContents(View view) {
        //Hier werden die EditText Felder mit den Infos aus dem Profil befüllt
        //... = profile.getName();
        //... = profile.getSize();
        //... = profile.getWeight();
        EditText editText = (EditText)view.findViewById(R.id.name_edit);
        editText.setText("Hallo"); //hier kommt dann Profile.getName() rein
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);


        setUp(view);





        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void changeOnState(View view){
        EditText weightText = view.findViewById(R.id.weight);
        EditText heightText = view.findViewById(R.id.height);
        EditText helloText = view.findViewById(R.id.name_edit);

        weightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                    hideKeyboard(view);
                }
            }
        });

        heightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                }
            }
        });

        helloText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                }
            }
        });
    }


}

