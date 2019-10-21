package com.example.fitnesstracker.fragments;

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

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.Sport;
import com.example.fitnesstracker.Workout;

public class Training extends Fragment {

    private Workout workout;
    private Sport sport;
    private int duration;
    private int calorieConsumption;

    AutoCompleteTextView addSport;
    NumberPicker addDuration;
    Button addWorkout;
    TextView consumedCalories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //setUp(view);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false);
    }

    private void setUp(View view) {
        duration = 0;

        addSport = view.findViewById(R.id.add_sport);
        addDuration = view.findViewById(R.id.add_duration);
        addWorkout = view.findViewById(R.id.add_workout);
        consumedCalories = view.findViewById(R.id.consumed_calories);

        setUpSportPicker();
        setUpDurationPicker();

        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Workout workout = new Workout(sport.toString(), duration);
                //insert workout in Datenbank
                consumedCalories.setText(workout.getCalorieConsumption());
            }
        });
    }

    private void setUpSportPicker() {
        String[] sports = getResources().getStringArray(R.array.sports_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, sports);
        addSport.setAdapter(adapter);
    }

    private void setUpDurationPicker() {

        addDuration.setMinValue(1);
        addDuration.setMaxValue(300);
        addDuration.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                duration = i1;
            }
        });
    }
}
