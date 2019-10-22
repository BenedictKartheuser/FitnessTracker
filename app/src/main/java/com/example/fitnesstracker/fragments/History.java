package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.Workout;
import com.example.fitnesstracker.dao.WorkoutDao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History extends Fragment {

    private List<Workout> history;
    final static long DAY_IN_MS = 1000 * 60 * 60 * 24;

    private RecyclerViewAdapter adapter;
    private WorkoutDao workoutDao;


    private List<Workout> setUp() {
        //History aus DB laden
        //history = ;
        history = new ArrayList<Workout>();
        return history;
    }

    /**
     * Berechnet verbrauchte Kalorien der letzten Woche
     * Liest Datum aus Workouts und vergleicht mit Datum von vor einer Woche
     * summiert Kalorien relevanter Workouts auf
     * @param history - Liste aller workouts, wird vorher aus DB geladen
     * @return verbrauchte Kalorien der letzten Woche via getCalories Methode
     */
    /*
    public int getLastWeekCalories(ArrayList<Workout> history) {
        int[] calories = new int[history.size()];
        Date workoutDate;
        Date lastWeek = getDateFromLastWeek();
        for (int i = 0; i < history.size(); i++) {
            workoutDate = getDateFromWorkout(history.get(i));
            if (workoutDate.compareTo(lastWeek) > 0) {
                calories[i] = history.get(i).calculateCalorieConsumption(history.get(i));
            }
        }
        return getCalories(calories);
    }*/

    /**
     *
     * @param calories Array mit allen verbrauchten Kalorien der letzten Woche
     * @return Gesamtverbrauch
     */
    private int getCalories(int[] calories) {
        int lastWeekCalories = 0;
        for (int i = 0; i < calories.length; i++) {
            lastWeekCalories += calories[i];
        }
        return lastWeekCalories;
    }

    /**
     * Ermittelt Datum aus einem Workout
     * @param workout - einzelnes Workout
     * @return Datum des workouts
     */
    private Date getDateFromWorkout(Workout workout) {
        Date date = new Date();
        try {
            date =  new SimpleDateFormat("dd-MM-yyyy").parse(workout.toString().substring(0, 10));
        } catch (Exception e) {
            //TODO
        }
        return date;

    }

    /**
     *
     * @return Datumsobjekt der letzten Woche
     */
    private Date getDateFromLastWeek() {
        return new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_history, container, false);

        workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerViewAdapter(setUp());
        recyclerView.setAdapter(adapter);
        new LadeWorkoutTask().execute();

        // Inflate the layout for this fragment
        return view;


    }

    public List<Workout> getHistory() {
        return history;
    }

    public void setHistory(List<Workout> history) {
        this.history = history;
    }

    class LadeWorkoutTask extends AsyncTask<Void, Void, List<Workout>>{

        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return workoutDao.getWorkout();
        }

        @Override
        protected  void onPostExecute(List<Workout> history){
            super.onPostExecute(history);
            adapter.setHistory(history);
        }
    }
}
