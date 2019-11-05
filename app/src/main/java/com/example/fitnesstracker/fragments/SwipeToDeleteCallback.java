package com.example.fitnesstracker.fragments;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.MainActivity;
import com.example.fitnesstracker.Workout;
import com.example.fitnesstracker.dao.WorkoutDao;
import com.example.fitnesstracker.fragments.FitnessDatabase;
import com.example.fitnesstracker.fragments.History;
import com.example.fitnesstracker.fragments.RecyclerViewAdapter;

import static java.security.AccessController.getContext;

/**
 * Swipe to Delete Handling
 */
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private RecyclerViewAdapter adapter;
    private WorkoutDao newWorkoutDao = Dashboard.workoutDao;

    public SwipeToDeleteCallback(RecyclerViewAdapter adapter){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    /**
     * Reaction when item swiped to delete
     * Delete item and update list
     * @param viewHolder holder
     * @param direction direction, irrelevant
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        final Workout workout = adapter.selectItem(position);


        /**
         * Async database access to remove swiped item
         */
        class DeleteItemTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                newWorkoutDao.delete(workout);
                return null;
            }
        }

        new DeleteItemTask().execute();
        adapter.updateList(position);
    }



}
