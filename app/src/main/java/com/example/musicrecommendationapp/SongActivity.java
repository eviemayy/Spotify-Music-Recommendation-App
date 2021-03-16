package com.example.musicrecommendationapp;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SongActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SongAdapter songAdapter;
    private SongViewModel songViewModel;
    private RecyclerView songsRV;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mood = sharedPreferences.getString(
                getString(R.string.pref_mood_key),
                getString(R.string.pref_mood_default_value)
        );
        Log.d("MOOOOOOOOOOOOOOD", "." + mood + ".");

        this.songAdapter = new SongAdapter(this.generateSimpleList(mood));
        this.songsRV = findViewById(R.id.simple_recyclerview);
        this.songsRV.setLayoutManager(new LinearLayoutManager(this));
        this.songsRV.setHasFixedSize(true);
        this.songsRV.setAdapter(this.songAdapter);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    private List<SongViewModel> generateSimpleList(String mood) {
        List<Object> newList = Collections.emptyList();
        Log.d("MOOOPOOOOOD", "SHOULD BE HERE NEXT");
        if (mood.trim().equals("sad")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.sad_songs));
        }
        else if (mood.trim().equals("workout")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.workout_songs));
        }
        else if (mood.trim().equals("happy")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_songs));
        }
        else if (mood.trim().equals("study")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.study_songs));
        }
        else if (mood.trim().equals("party")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.party_songs));
        }
        else {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_songs));
        }
        List<SongViewModel> simpleViewModelList = new ArrayList<>();
        //String songName = "    " + getString(R.string.party_song_still_brazy_name);

        for (int i = 0; i < newList.size(); i++) {
            simpleViewModelList.add(new SongViewModel(String.format(Locale.US, (String) newList.get(i))));
        }

        return simpleViewModelList;
    }


    @Override
    protected void onDestroy() {
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        String mood = sharedPreferences.getString(
                getString(R.string.pref_mood_key),
                getString(R.string.pref_mood_default_value)
        );

        switch (mood) {
            case "happy":
                Log.d(TAG, "mood1: " + mood);
                return;
            case "sad":
                Log.d(TAG, "mood2: " + mood);
                return;
            case "study":
                Log.d(TAG, "mood3: " + mood);
                return;
            case "workout":
                Log.d(TAG, "mood4: " + mood);
                return;
            case "party":
                Log.d(TAG, "mood5: " + mood);
                return;
            default:
                Log.d(TAG, "Mood isn't selected");
                return;
        }
    }
}
