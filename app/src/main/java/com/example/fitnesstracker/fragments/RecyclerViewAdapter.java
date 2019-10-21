package com.example.fitnesstracker.fragments;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.Workout;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private ArrayList<Workout> history;

    public RecyclerViewAdapter (ArrayList<Workout> history) {
        this.history = history;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        ItemViewHolder ivh = new ItemViewHolder(view);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CardView historyItem = holder.itemView.findViewById(R.id.history_item);
        TextView calories = holder.itemView.findViewById(R.id.calories);
        TextView sport = holder.itemView.findViewById(R.id.sport);
        TextView time = holder.itemView.findViewById(R.id.time);

        calories.setText(history.get(position).getCalorieConsumption());
        sport.setText(history.get(position).getSport());
        time.setText(history.get(position).getDuration() + " - " + history.get(position).getTimestamp());

    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
