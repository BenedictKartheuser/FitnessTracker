package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.Workout;
import com.example.fitnesstracker.dao.WorkoutDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History extends Fragment {

    private final static long DAY_IN_MS = 1000 * 60 * 60 * 24;

    private RecyclerViewAdapter adapter;
    private WorkoutDao workoutDao;

    /**
     * Berechnet verbrauchte Kalorien der letzten Woche
     * Liest Datum aus Workouts und vergleicht mit Datum von vor einer Woche
     * summiert Kalorien relevanter Workouts auf
     * @param history - Liste aller workouts, wird vorher aus DB geladen
     * @return verbrauchte Kalorien der letzten Woche via getCalories Methode
     */

    public static int getLastWeekCalories(List<Workout> history) {
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
    }

    /**
     *
     * @param calories Array mit allen verbrauchten Kalorien der letzten Woche
     * @return Gesamtverbrauch
     */
    private static int getCalories(int[] calories) {
        int lastWeekCalories = 0;

        for (int i : calories) {
            lastWeekCalories += i;
        }
        return lastWeekCalories;
    }

    /**
     * Ermittelt Datum aus einem Workout
     * @param workout - einzelnes Workout
     * @return Datum des workouts
     */
    private static Date getDateFromWorkout(Workout workout) {
        Date date = new Date();
        try {
            date =  new SimpleDateFormat("dd-MM-yyyy").parse(workout.toString());
        } catch (Exception e) {
            //TODO
        }
        return date;
    }

    /**
     *
     * @return Datumsobjekt der letzten Woche
     */
    private static Date getDateFromLastWeek() {
        return new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerViewAdapter(new ArrayList<Workout>());
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadWorkoutTask().execute();
    }

    class LoadWorkoutTask extends AsyncTask<Void, Void, List<Workout>>{

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
