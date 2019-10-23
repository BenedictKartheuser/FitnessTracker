package com.example.fitnesstracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnesstracker.Profile;
import com.example.fitnesstracker.R;

public class Dashboard extends Fragment {

    private View root;
    private TextView hello_text, exe_text, week_text, kcal_text, height_text, weight_text;
    private EditText name_edit, height_edit, weight_edit;
    private CardView dash_card;
    private Profile profile;
    private int lastWeek;




    public void setUp(View view) {
        //Hier müsste profile aus der datenbank geladen werden
        //profile = DAO;
        if (profile == null) {
            profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_SIZE, Profile.DEFAULT_WEIGHT);
        }
        setContents(view);

        //nicht new History eigentlich, sondern History aus DB laden
        History history = new History();
        //lastWeek = history.getLastWeekCalories(history.getHistory());
    }

    private void setContents(View view) {
        //Hier werden die EditText Felder mit den Infos aus dem Profil befüllt
        //... = profile.getName();
        //... = profile.getSize();
        //... = profile.getWeight();
        EditText editText = (EditText)view.findViewById(R.id.name_edit);
        editText.setText("Hallo"); //hier kommt dann Profile.getName() rein
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        addToView(root);
        changeOnState(root);
        setUp(root);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public void addToView(View root){

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


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }



    public void changeOnState(View view){
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

