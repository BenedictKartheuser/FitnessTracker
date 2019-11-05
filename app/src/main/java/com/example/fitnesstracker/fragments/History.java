package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
     * Calculates last weeks calorie consumption
     * Uses date from workout to find relevant workouts
     * sums up relevant workouts
     * @param history all workouts as a listed fetched from database
     * @return calorie consumption from last week
     */

    public static int getLastWeekCalories(List<Workout> history) {
        int[] calories = new int[history.size()];                       //Array for calories only
        Date workoutDate;
        Date lastWeek = getDateFromLastWeek();
        for (int i = 0; i < history.size(); i++) {
            workoutDate = getDateFromWorkout(history.get(i));
            if (workoutDate.compareTo(lastWeek) > 0) {                  //if workout was within last week:
                calories[i] = history.get(i).getCalorieConsumption();   //add to Array
            }
        }
        return getCalories(calories);                                   //Method to sum up
    }

    /**
     * Sums up calories from array
     * @param calories array with calories from last week
     * @return calorie consumption from last week
     */
    private static int getCalories(int[] calories) {
        int lastWeekCalories = 0;

        for (int i : calories) {
            lastWeekCalories += i;
        }
        return lastWeekCalories;
    }

    /**
     * Gets date of workout
     * @param workout single workout
     * @return Date of workout
     */
    private static Date getDateFromWorkout(Workout workout) {
        Date date = new Date();
        try {
            date =  new SimpleDateFormat("dd-MM-yyyy").parse(workout.toString());
        } catch (Exception e) {
            Log.println(Log.WARN,"PARS", "Parsing error");
        }
        return date;
    }

    /**
     * calculates last weeks date
     * @return last weeks Date
     */
    private static Date getDateFromLastWeek() {
        return new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_history, container, false);

        setUp(root);    //set up contents and helpers
        return root;
    }

    /**
     * Set up view and contents
     * @param root view
     */
    private void setUp(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new RecyclerViewAdapter(new ArrayList<Workout>());
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        workoutDao = FitnessDatabase.getDatabase(getContext()).workoutDao();
    }

    /**
     * async load workouts to histoy on resume
     */
    @Override
    public void onResume() {
        super.onResume();
        new LoadWorkoutTask().execute();
    }

    /**
     *  Load workouts from database
     */
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
