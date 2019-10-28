package com.example.fitnesstracker.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.Workout;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private List<Workout> history;

    public RecyclerViewAdapter (List<Workout> history) {
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

        TextView calories = holder.itemView.findViewById(R.id.calories);
        TextView sport = holder.itemView.findViewById(R.id.sport);
        TextView time = holder.itemView.findViewById(R.id.detail);

        calories.setText(String.valueOf(history.get(position).getCalorieConsumption()));
        sport.setText(history.get(position).getSport().split(",")[0]);
        String detail = "Duration:" + history.get(position).getDuration() + "min - Date: "
                + history.get(position).getTimestamp() + "/n Height: " + history.get(position).getProfile().split(",")[1]
                + "cm - Weight: " + history.get(position).getProfile().split(",")[2] + "kg";
        time.setText(detail);

    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void setHistory(List<Workout> history){
        this.history = history;
        notifyDataSetChanged();
    }

    public Workout selectItem(int position){
        Workout workout = history.get(position);
        return workout;


    }

    public void updateList(int position){
        history.remove(position);
        notifyItemRemoved(position);
    }



}
