package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.*;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.fitnesstracker.dao.ProfileDao;
import com.example.fitnesstracker.dao.SportDao;
import com.example.fitnesstracker.dao.WorkoutDao;
import com.example.fitnesstracker.fragments.Dashboard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {

    //kommt die Logik überhaupt hier rein?
    //oder braucht man ne Klasse App?
    private Profile profile;

    //DAOs der einzelnen Entities
    private ProfileDao pdao;
    private SportDao sdao;
    private WorkoutDao wdao;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DAO einbinden
        pdao = FitnessDatabase.getDatabase(this).profileDao();
        sdao = FitnessDatabase.getDatabase(this).sportDao();
        wdao = FitnessDatabase.getDatabase(this).workoutDao();

        //Bottomnavigation erstellen
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_dashboard, R.id.navigation_training,
                R.id.navigation_history, R.id.navigation_howto).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //Set Up Mehthod for loading profiile, history etc.
        //setUp();



    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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





}
