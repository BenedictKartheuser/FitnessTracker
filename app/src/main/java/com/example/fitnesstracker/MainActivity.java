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

public class MainActivity extends AppCompatActivity {

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

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
