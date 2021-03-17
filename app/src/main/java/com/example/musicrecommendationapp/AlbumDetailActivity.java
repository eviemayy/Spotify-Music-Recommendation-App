package com.example.musicrecommendationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlbumDetailActivity extends AppCompatActivity {

    private static final String TAG = AlbumDetailActivity.class.getSimpleName();
    public static final String EXTRA_ALBUM_DATA = "AlbumDetailActivity.AlbumData";
    private String albumName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_ALBUM_DATA)){
            this.albumName = (String)intent.getSerializableExtra(EXTRA_ALBUM_DATA);
            Log.d(TAG, "Album name: " + albumName);
            TextView albumUriTV = findViewById(R.id.tv_placeholder);
            albumUriTV.setText(albumName);

        }

    }


}
