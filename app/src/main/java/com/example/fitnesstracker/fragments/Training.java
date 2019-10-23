package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

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

    private AutoCompleteTextView addSport;
    private NumberPicker addDuration;
    private Button addWorkout;
    private TextView consumedCalories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_training, container, false);

        setUp(root);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    private void setUp(View root) {
        duration = 0;

        addSport = root.findViewById(R.id.add_sport);
        addDuration = root.findViewById(R.id.add_duration);
        addWorkout = root.findViewById(R.id.add_workout);
        consumedCalories = root.findViewById(R.id.consumed_calories);

        setUpSportPicker();
        setUpDurationPicker();

        profileDao = FitnessDatabase.getDatabase(getContext()).profileDao();

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                //Wo genau Sport herkommt muss noch geklärt werden
                workout = new Workout(sport.toString(), duration, profile.toString());
                workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();
                new SaveWorkoutTask().execute(workout);
                consumedCalories.setText(workout.getCalorieConsumption());
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

    private void setUpSportPicker() {
        String[] sports = getResources().getStringArray(R.array.sports_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, sports);
        addSport.setAdapter(adapter);
    }

    private void setUpDurationPicker() {

        addDuration.setMinValue(1);
        addDuration.setMaxValue(300);
        addDuration.setWrapSelectorWheel(true);
        addDuration.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                duration = i1;
            }
        });
    }
}
