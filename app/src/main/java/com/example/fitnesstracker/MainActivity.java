package com.example.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;


import com.example.fitnesstracker.fragments.Dashboard;
import com.example.fitnesstracker.fragments.History;
import com.example.fitnesstracker.fragments.HowTo;
import com.example.fitnesstracker.fragments.Training;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Main and only Activity
 * App uses Fragments
 */
public class MainActivity extends AppCompatActivity {

    // Fragments
    private Dashboard dashFrag;
    private Training trainFrag;
    private History histFrag;
    private HowTo howToFrag;


    /**
     * Listener for Bottom Navigation Bar to handle fragments
     * Handling via Switch Case
     */
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
                    selectedFrag = howToFrag;
                    setTitle(R.string.nav_howto);
                    break;
                default: selectedFrag = dashFrag;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    selectedFrag).commit();
            return true;
        }

    };

    /**
     * Creates bottomNav
     * sets up Fragments
     * @param savedInstanceState state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Einzelne Fragmente auf die zugegriffen werden soll
        dashFrag = new Dashboard();
        trainFrag = new Training();
        histFrag = new History();
        howToFrag = new HowTo();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, dashFrag)
                .commit();
        setTitle(R.string.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }
}
