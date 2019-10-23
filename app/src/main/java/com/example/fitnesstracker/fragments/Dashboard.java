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
import com.example.fitnesstracker.dao.ProfileDao;

public class Dashboard extends Fragment {

    private View root;
    private TextView hello_text, exe_text, week_text, kcal_text, height_text, weight_text;
    private EditText name_edit, height_edit, weight_edit;
    private CardView dash_card;
    private Profile profile;
    private int lastWeek;

    ProfileDao profileDao;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        profileDao = FitnessDatabase.getDatabase(getContext()).profileDao();

        addToView(root);
        changeOnState(root);
        setUp(root);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        new Dashboard.LoadProfileTask().execute();
    }

    class LoadProfileTask extends AsyncTask<Void, Void, Profile> {

        @Override
        protected Profile doInBackground(Void... voids) {
            return profileDao.getProfile();
        }

        @Override
        protected  void onPostExecute(Profile loadedProfile){
            profile = loadedProfile;
            super.onPostExecute(profile);

        }
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

    public void setUp(View root) {
        if (profile == null) {
            profile = new Profile(Profile.DEFAULT_NAME, Profile.DEFAULT_SIZE, Profile.DEFAULT_WEIGHT);
            Log.println(Log.WARN, "1", "hierr");
            Log.println(Log.WARN, "1", String.valueOf(profile.getName()));
        }

        setContents(root);
        setListeners();

        //nicht new History eigentlich, sondern History aus DB laden
        History history = new History();
        //lastWeek = history.getLastWeekCalories(history.getHistory());
    }

    private void setContents(View view) {
        //Hier werden die EditText Felder mit den Infos aus dem Profil befüllt
        name_edit.setText(profile.getName());
        height_edit.setText(Integer.toString(profile.getHeight()));
        weight_edit.setText(Integer.toString(profile.getWeight()));
        EditText editText = (EditText)view.findViewById(R.id.name_edit);
        editText.setText("Hallo"); //hier kommt dann Profile.getName() rein
    }

    private void setListeners() {
        name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                profileDao.updateName(charSequence.toString());
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
                profileDao.updateHeight(Integer.parseInt(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        weight_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                profileDao.updateWeight(Integer.parseInt(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
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

