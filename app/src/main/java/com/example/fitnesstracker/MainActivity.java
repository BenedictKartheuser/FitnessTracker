package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.*;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.fitnesstracker.dao.ProfileDao;
import com.example.fitnesstracker.dao.SportDao;
import com.example.fitnesstracker.dao.WorkoutDao;
import com.example.fitnesstracker.fragments.Dashboard;
import com.example.fitnesstracker.fragments.History;
import com.example.fitnesstracker.fragments.HowTo;
import com.example.fitnesstracker.fragments.Training;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {

    private Profile profile;

    private Dashboard dashFrag;
    private Training trainFrag;
    private History histFrag;
    private HowTo howtoFrag;


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag;
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    //switchToDashboard();
                    selectedFrag = dashFrag;
                    setTitle(R.string.dashboard);
                    break;
                case R.id.navigation_training:
                    //switchToTraining();
                    selectedFrag = trainFrag;
                    setTitle(R.string.nav_training);
                    break;
                case R.id.navigation_history:
                    //switchToHistory();
                    selectedFrag = histFrag;
                    setTitle(R.string.nav_history);
                    break;
                case R.id.navigation_howto:
                    //switchToHowTo();
                    selectedFrag = howtoFrag;
                    setTitle(R.string.nav_howto);
                    break;
                default: selectedFrag = dashFrag;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    selectedFrag).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Einzelne Fragmente auf die zugegriffen werden soll
        dashFrag = new Dashboard();
        trainFrag = new Training();
        histFrag = new History();
        howtoFrag = new HowTo();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, dashFrag)
                .commit();
        setTitle(R.string.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);



        /*
        //Bottomnavigation erstellen
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_dashboard, R.id.navigation_training,
        R.id.navigation_history, R.id.navigation_howto).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //Set Up Mehthod for loading profiile, history etc.
        //setUp();

         */






    }

    /*
    public void switchToDashboard() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_dashboard, new Dashboard()).commit();
    }

    public void switchToTraining() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_training, new Training()).commit();
    }

    public void switchToHistory() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_history, new History()).commit();
    }

    public void switchToHowTo() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_how_to, new HowTo()).commit();
    }

     */

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
