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
import androidx.preference.PreferenceManager;

public class AlbumDetailActivity extends AppCompatActivity {

    private static final String TAG = AlbumDetailActivity.class.getSimpleName();
    public static final String EXTRA_ALBUM_DATA = "AlbumDetailActivity.AlbumData";

    private SharedPreferences sharedPreferences;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_share:
                shareAlbum();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareAlbum(){
        Log.d(TAG, "shareAlbum");
    }


}
