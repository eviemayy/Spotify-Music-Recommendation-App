package com.example.musicrecommendationapp;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SongActivity extends AppCompatActivity
        implements SongAdapter.OnSongItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = SongActivity.class.getSimpleName();
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

        this.songAdapter = new SongAdapter(this, this.generateSimpleList(mood));
        this.songsRV = findViewById(R.id.simple_recyclerview);
        this.songsRV.setLayoutManager(new LinearLayoutManager(this));
        this.songsRV.setHasFixedSize(true);
        this.songsRV.setAdapter(this.songAdapter);
    }


    private List<SongViewModel> generateSimpleList(String mood) {
        List<Object> newList = Collections.emptyList();
        List<Object> nameList = Collections.emptyList();
        Log.d("MOOOPOOOOOD", "SHOULD BE HERE NEXT");
        if (mood.trim().equals("sad")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.sad_songs));
            nameList = Arrays.asList(getResources().getStringArray(R.array.sad_songs_names));
        }
        else if (mood.trim().equals("workout")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.workout_songs));
            nameList = Arrays.asList(getResources().getStringArray(R.array.workout_songs_names));
        }
        else if (mood.trim().equals("happy")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_songs));
            nameList = Arrays.asList(getResources().getStringArray(R.array.happy_songs_names));
        }
        else if (mood.trim().equals("study")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.study_songs));
            nameList = Arrays.asList(getResources().getStringArray(R.array.study_songs_names));
        }
        else if (mood.trim().equals("party")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.party_songs));
            nameList = Arrays.asList(getResources().getStringArray(R.array.party_songs_names));
        }
        else {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_songs));
            nameList = Arrays.asList(getResources().getStringArray(R.array.happy_songs_names));
        }
        List<SongViewModel> simpleViewModelList = new ArrayList<>();
        //String songName = "    " + getString(R.string.party_song_still_brazy_name);

        for (int i = 0; i < newList.size(); i++) {
            simpleViewModelList.add(new SongViewModel(String.format(Locale.US, (String) nameList.get(i))));
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

    @Override
    public void onSongItemClick(String songUri) {
        Log.d("SongActivity", "CLICKED IN SONG ACITIVYT!!!!!");

        Intent intent = new Intent(this, SongDetailActivity.class);
        intent.putExtra(SongDetailActivity.EXTRA_SONG_DATA, songUri);
        startActivity(intent);
    }
}
