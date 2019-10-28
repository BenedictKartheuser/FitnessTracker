package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.R;
import com.example.fitnesstracker.Workout;
import com.example.fitnesstracker.dao.ProfileDao;
import com.example.fitnesstracker.dao.WorkoutDao;

import java.util.List;

public class Dashboard extends Fragment {

    private View root;
    private TextView hello_text, exe_text, week_text, kcal_text, height_text, weight_text;
    private EditText name_edit, height_edit, weight_edit;
    private CardView dash_card;
    private Profile profile;
    private int lastWeek;
    private String profileName;
    private int heightValue;
    private int weightValue;

    private List<Workout> workouts;

    private ProfileDao profileDao;
    protected static WorkoutDao workoutDao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setUp();

        // Inflate the layout for this fragment
        return root;
    }

    private void setUp() {
        profileDao = FitnessDatabase.getDatabase(getContext()).profileDao();
        workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();

        addToView(root);
        changeOnState(root);
        setEditTextListeners();
    }

    private void setEditTextListeners() {
        name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()) {
                    profileName = "user";
                    name_edit.setText("User");
                } else {
                    profileName = charSequence.toString();
                }
                new ChangeNameTask().execute(profileName);
                Log.println(Log.WARN, "1", "update");
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        height_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()) {
                    heightValue = 1;
                    height_edit.setText("1");
                } else {
                    heightValue = Integer.parseInt(charSequence.toString());
                }
                new ChangeHeightTask().execute(heightValue);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        weight_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
                    weightValue = 1;
                    weight_edit.setText("1");
                } else {
                    weightValue = Integer.parseInt(charSequence.toString());
                }
                new ChangeWeightTask().execute(weightValue);

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    class ChangeNameTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            profileDao.updateName(profileName);
            Log.println(Log.WARN, "1", "Update Name: " + profileName);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class ChangeWeightTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... ints) {
            profileDao.updateWeight(weightValue);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    class ChangeHeightTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... ints) {
            profileDao.updateHeight(heightValue);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new Dashboard.CreateProfileTask().execute();
        new Dashboard.LoadProfileTask().execute();
        new Dashboard.LoadWorkoutTask().execute();
    }

    class CreateProfileTask extends  AsyncTask<Profile, Void, Void> {

        @Override
        protected Void doInBackground(Profile... profiles) {
            profileDao.insertProfile(new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_HEIGHT, Profile.DEFAULT_WEIGHT));
            Log.println(Log.WARN, "1", "could create profile");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) { super.onPostExecute(aVoid); }
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
                Log.println(Log.WARN, "1", "Loaded Profile = null");
            } else {
                Log.println(Log.WARN, "1", "Could load Profile: " + loadedProfile.get(0));
                profile = loadedProfile.get(0);
            }
            name_edit.setText(profile.getName());
            height_edit.setText(String.valueOf(profile.getHeight()));
            weight_edit.setText(String.valueOf(profile.getWeight()));
            super.onPostExecute(loadedProfile);

        }
    }

    class LoadWorkoutTask extends AsyncTask<Void, Void, List<Workout>>{

        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return workoutDao.getWorkout();
        }

        @Override
        protected  void onPostExecute(List<Workout> history){
            super.onPostExecute(history);
            workouts = history;
            lastWeek = History.getLastWeekCalories(workouts);
            Log.println(Log.WARN, "1", "LastWeek Calories" + lastWeek);
            String kcalText = lastWeek + " kcal";
            kcal_text.setText(kcalText);
        }
    }

    private void addToView(View root){

        //TextView
        hello_text = root.findViewById(R.id.hello_text);
        exe_text = root.findViewById(R.id.ex_mark);
        week_text = root.findViewById(R.id.week_text);
        kcal_text = root.findViewById(R.id.kcal_text);
        height_text = root.findViewById(R.id.height_text);
        weight_text = root.findViewById(R.id.weight_text);

        //EditText
        name_edit = root.findViewById(R.id.name_edit);
        height_edit = root.findViewById(R.id.height);
        weight_edit = root.findViewById(R.id.weight);

        //CardView
        dash_card = root.findViewById(R.id.card_view);


    }


    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    private void changeOnState(View view){
        EditText weightText = view.findViewById(R.id.weight);
        EditText heightText = view.findViewById(R.id.height);
        EditText helloText = view.findViewById(R.id.name_edit);


        weight_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);

                }
            }
        });

        height_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                }
            }
        });

        name_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard(view);
                }
            }
        });
    }


}

