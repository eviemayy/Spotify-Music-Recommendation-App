package com.example.musicrecommendationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ArtistDetailActivity extends AppCompatActivity {

    private static final String TAG = ArtistDetailActivity.class.getSimpleName();
    public static final String EXTRA_ARTIST_DATA = "ArtistDetailActivity.ArtistData";
    private String artistName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_ARTIST_DATA)){
            this.artistName = (String)intent.getSerializableExtra(EXTRA_ARTIST_DATA);
            Log.d(TAG, "Artist name: " + artistName);
            TextView artistUriTV = findViewById(R.id.tv_placeholder);
            artistUriTV.setText(artistName);

        }

    }


}
