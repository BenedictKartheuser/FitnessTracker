package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import java.util.List;

public class Training extends Fragment {

    private Workout workout;
    private Sport sport;
    private int duration;
    private Profile profile;

    private WorkoutDao workoutDao;
    private ProfileDao profileDao;

    private AutoCompleteTextView addSport;
    private NumberPicker addDuration;
    private Button addWorkout;
    private TextView consumedCalories;

    private boolean sportPicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_training, container, false);
        addToView(root);
        setUp();


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadProfileTask().execute();
    }

    private void addToView(View root){
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
        profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_HEIGHT, Profile.DEFAULT_WEIGHT);

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println(Log.WARN, "1", "Clicked Add Button");
                Log.println(Log.WARN, "1", "Sport Picked: " + sportPicked);
                if(!sportPicked) {
                    Toast.makeText(getContext(),"Make sure to choose sport and duration",Toast.LENGTH_SHORT).show();
                    return;
                }
                workout = new Workout(sport.toString(), duration, profile.toString());
                workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();
                new SaveWorkoutTask().execute(workout);
                consumedCalories.setText(String.valueOf(workout.getCalorieConsumption()));
                resetInputFields();
            }
        });

    }

    private void resetInputFields() {
        sportPicked = false;
        addSport.setText("");
        addDuration.setValue(1);
    }

    private void setUpSportPicker() {
        String[] sports = getResources().getStringArray(R.array.sports_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, sports);
        addSport.setThreshold(1);
        addSport.setAdapter(adapter);
        addSport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] sports = getResources().getStringArray(R.array.sports_and_factor_array);
                sport = new Sport(sports[(int) l]);
                sportPicked = true;
                Log.println(Log.WARN, "1", "Position: " + i);
                Log.println(Log.WARN, "1", "Was weiss ich: " + l);
            }
        });
    }

    private void setUpDurationPicker() {

        addDuration.setMaxValue(300);
        addDuration.setMinValue(1);
        addDuration.setWrapSelectorWheel(true);
        duration = 1;
        addDuration.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                duration = i1;
            }
        });
    }

    class LoadProfileTask extends AsyncTask<Void, Void, List<Profile>> {

        @Override
        protected List<Profile> doInBackground(Void... voids) {
            return profileDao.getProfile();
        }

        @Override
        protected  void onPostExecute(List<Profile> loadedProfile){
            if (loadedProfile == null) {
                profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_HEIGHT, Profile.DEFAULT_WEIGHT);
                Log.println(Log.WARN, "1", "Training: Loaded Profile = null");
            } else {
                Log.println(Log.WARN, "1", "Training: Could load Profile: " + loadedProfile.get(0));
                profile = loadedProfile.get(0);
            }
            super.onPostExecute(loadedProfile);

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
