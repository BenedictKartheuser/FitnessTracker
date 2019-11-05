package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Add Workout Fragment
 */
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
        setUp(root);

        return root;
    }

    /**
     * Load Profile on Resume
     */
    @Override
    public void onResume() {
        super.onResume();
        new LoadProfileTask().execute();
    }

    /**
     * Add elements to view
     * @param root view
     */
    private void addToView(View root){
        duration = 0;

        addSport = root.findViewById(R.id.add_sport);
        addDuration = root.findViewById(R.id.add_duration);

        consumedCalories = root.findViewById(R.id.consumed_calories);
    }

    /**
     * set up contents and daos
     */
    private void setUp(View root) {

        duration = 0;
        setUpSportPicker();     //set up single elements and listeners
        setUpDurationPicker();
        changeOnState(root);

        profileDao = FitnessDatabase.getDatabase(getContext()).profileDao();
        profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_HEIGHT, Profile.DEFAULT_WEIGHT);

        addWorkout = root.findViewById(R.id.add_workout);
        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sportPicked) {  //make sure input is valid
                    Toast.makeText(getContext(),"Make sure to choose sport and duration",Toast.LENGTH_SHORT).show();
                    return;
                }
                workout = new Workout(sport.toString(), duration, profile.toString());
                workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();
                new SaveWorkoutTask().execute(workout);
                String caloriesConsumed = "Consumed Calories: \n" + workout.getCalorieConsumption();
                consumedCalories.setText(caloriesConsumed);
                resetInputFields();     //reset input fields after workout added
            }
        });

    }

    /**
     * reset input fields and values after workout added
     */
    private void resetInputFields() {
        sportPicked = false;
        addSport.setText("");
        addDuration.setValue(1);
        duration = 1;
    }

    /**
     * Set up Sport picker
     * sport lists in sport.xml
     * 2 different lists
     * set Listeners
     */
    private void setUpSportPicker() {
        String[] sports = getResources().getStringArray(R.array.sports_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, sports);
        addSport.setThreshold(1);
        addSport.setAdapter(adapter);
        addSport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] sports = getResources().getStringArray(R.array.sports_and_factor_array);
                String content = addSport.getText().toString();

                for (String s : sports) {       //search sport from Sports string. There are two different sport string arrays
                                                //one without and one with factors (seperated by ",")
                    if (s.split(",")[0].equals(content)) {
                        sport = new Sport(s);   //get Sport from input in AutoCompleteTextField
                        sportPicked = true;     //needed for verification when add button pressed
                    }
                }
            }
        });
    }

    /**
     * Set up duration picker
     * set Listeners
     */
    private void setUpDurationPicker() {

        addDuration.setMaxValue(300);
        addDuration.setMinValue(1);
        addDuration.setWrapSelectorWheel(true);
        duration = 1;
        addDuration.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                duration = i1;      //get and save value on Change
            }
        });
    }

    /**
     * handling to close keyboard when clicked somewhere else
     * @param view view
     */
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    /**
     * handling to close keyboard when clicked somewhere else
     * @param view view
     */
    private void changeOnState(View view){

        addSport.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                }
            }
        });

        addDuration.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                }
            }
        });

    }

    /**
     * Async Load profile from Database
     */
    class LoadProfileTask extends AsyncTask<Void, Void, List<Profile>> {

        @Override
        protected List<Profile> doInBackground(Void... voids) {
            return profileDao.getProfile();
        }

        @Override
        protected  void onPostExecute(List<Profile> loadedProfile){
            if (loadedProfile == null) {
                profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_HEIGHT, Profile.DEFAULT_WEIGHT);
            } else {
                profile = loadedProfile.get(0);
            }
            super.onPostExecute(loadedProfile);

        }
    }

    /**
     * Async save workout to database
     */
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
