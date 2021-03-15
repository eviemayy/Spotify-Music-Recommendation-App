package com.example.musicrecommendationapp;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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

        this.songAdapter = new SongAdapter(this.generateSimpleList());
        this.songsRV = findViewById(R.id.simple_recyclerview);
        this.songsRV.setLayoutManager(new LinearLayoutManager(this));
        this.songsRV.setHasFixedSize(true);
        this.songsRV.setAdapter(this.songAdapter);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private List<SongViewModel> generateSimpleList() {
        List<SongViewModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            simpleViewModelList.add(new SongViewModel(String.format(Locale.US, "This is song %d", i)));
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
