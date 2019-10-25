package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.R;
import com.example.fitnesstracker.dao.ProfileDao;
import com.example.fitnesstracker.dao.WorkoutDao;
import com.example.fitnesstracker.Sport;
import com.example.fitnesstracker.Workout;

public class Training extends Fragment {

    private Workout workout;
    private Sport sport;
    private int duration;
    private Profile profile;

    private WorkoutDao workoutDao;
    private ProfileDao profileDao;

    private View root;
    private AutoCompleteTextView addSport;
    private NumberPicker addDuration;
    private Button addWorkout;
    private TextView consumedCalories;

    private boolean sportPicked = false;
    private boolean durationPicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_training, container, false);
        addToView(root);
        setUp();


        // Inflate the layout for this fragment
        return root;
    }

    public void addToView(View root){
        duration = 0;

        addSport = root.findViewById(R.id.add_sport);
        addDuration = root.findViewById(R.id.add_duration);
        addWorkout = root.findViewById(R.id.add_workout);
        consumedCalories = root.findViewById(R.id.consumed_calories);


    }

    private void setUp() {

        duration = 0;
        setUpSportPicker();
        setUpDurationPicker();

        profileDao = FitnessDatabase.getDatabase(getContext()).profileDao();
        Log.println(Log.WARN, "1", "Add listener");

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.WARN, "1", "Clicked Add Button");
                if(!sportPicked || !durationPicked) {
                    Log.println(Log.WARN, "1", "invalid input");
                    Toast.makeText(getContext(),"Make sure to choose sport and duration",Toast.LENGTH_SHORT).show();
                    return;
                }
                workout = new Workout(sport.toString(), duration, profile.toString());
                workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();
                new SaveWorkoutTask().execute(workout);
                consumedCalories.setText(workout.getCalorieConsumption());

                sportPicked = false;
                durationPicked = false;
            }
        });

    }

    private void setUpSportPicker() {
        String[] sports = getResources().getStringArray(R.array.sports_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, sports);
        addSport.setThreshold(1);
        addSport.setAdapter(adapter);
    }

    private void setUpDurationPicker() {

        addDuration.setMaxValue(300);
        addDuration.setMinValue(1);
        addDuration.setWrapSelectorWheel(true);
        addDuration.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                duration = i1;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadProfileTask().execute();
    }

    class LoadProfileTask extends AsyncTask<Void, Void, Profile>{

        @Override
        protected Profile doInBackground(Void... voids) {
            return profileDao.getProfile();
        }

        @Override
        protected  void onPostExecute(Profile loadedProfile){
            profile = loadedProfile;
            super.onPostExecute(profile);

        }
    }

    class SaveWorkoutTask extends AsyncTask<Workout, Void, Void> {

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.insert(workout);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
