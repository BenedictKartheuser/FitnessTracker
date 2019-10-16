package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.fitnesstracker.dao.ProfileDao;
import com.example.fitnesstracker.dao.SportDao;
import com.example.fitnesstracker.dao.WorkoutDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //kommt die Logik überhaupt hier rein?
    //oder braucht man ne Klasse App?
    private Profile profile;

    //DAOs der einzelnen Entities
    private ProfileDao pdao;
    private SportDao sdao;
    private WorkoutDao wdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DAO einbinden
        pdao = FitnessDatabase.getDatabase(this).profileDao();
        sdao = FitnessDatabase.getDatabase(this).sportDao();
        wdao = FitnessDatabase.getDatabase(this).workoutDao();

        //Bottomnavigation erstellen
        BottomNavigationView bn = findViewById(R.id.bottom_navigation);

        bn.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_dashboard:

                            case R.id.navigation_training:

                            case R.id.navigation_history:

                            case R.id.navigation_howto:

                        }
                        return true;
                    }
                }
        );
        //Set Up Mehthod for loading profiile, history etc.
        //setUp();

        //Activates the OnFocusChangeListener on the height & weight edit fields to hide the keyboard and save the changes.
        changeOnState();

    }

    public void setUp() {
        //Hier müsste profile aus der datenbank geladen werden
        //profile = DAO;
        if (profile == null) {
            profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_SIZE, Profile.DEFAULT_WEIGHT);
        }

        //History (?) muss aus DB geladen werden
        //history = ?
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void changeOnState(){
        EditText weightText = findViewById(R.id.weight);
        EditText heightText = findViewById(R.id.height);
        EditText helloText = findViewById(R.id.name_edit);

        weightText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
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
