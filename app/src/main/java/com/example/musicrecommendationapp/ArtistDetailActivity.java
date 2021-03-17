package com.example.musicrecommendationapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ArtistDetailActivity extends AppCompatActivity {

    private static final String TAG = ArtistDetailActivity.class.getSimpleName();
    public static final String EXTRA_ARTIST_DATA = "ArtistDetailActivity.ArtistData";
    private String artistName;

    private SharedPreferences sharedPreferences;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_share:
                shareArtist();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareArtist(){
        Log.d(TAG, "shareArtist");
    }


}
