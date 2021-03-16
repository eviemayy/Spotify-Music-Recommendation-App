package com.example.musicrecommendationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class SongDetailActivity extends AppCompatActivity {

    private static final String TAG = SongDetailActivity.class.getSimpleName();
    public static final String EXTRA_SONG_DATA = "SongDetailActivity.SongData";
    private String songName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_SONG_DATA)){
            this.songName = (String)intent.getSerializableExtra(EXTRA_SONG_DATA);
            Log.d(TAG, "Song name: " + songName);
            TextView songUriTV = findViewById(R.id.tv_placeholder);
            songUriTV.setText(songName);

        }

    }
}
