package com.example.musicrecommendationapp;

import android.content.Intent;
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

public class AlbumActivity extends AppCompatActivity
        implements AlbumAdapter.OnAlbumItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener{


    private AlbumAdapter albumAdapter;
    private AlbumViewModel albumViewModel;
    private RecyclerView albumRV;
    private SharedPreferences sharedPreferences;

    private static final String TAG = AlbumActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mood = sharedPreferences.getString(
                getString(R.string.pref_mood_key),
                getString(R.string.pref_mood_default_value)
        );
        Log.d("MOOOOOOOOOOOOOOD", "." + mood + ".");

        this.albumAdapter = new AlbumAdapter(this, this.generateSimpleList(mood));
        this.albumRV = findViewById(R.id.simple_recyclerview);
        this.albumRV.setLayoutManager(new LinearLayoutManager(this));
        this.albumRV.setHasFixedSize(true);

        this.albumRV.setAdapter(this.albumAdapter);
    }

    private List<AlbumViewModel> generateSimpleList(String mood) {
        List<Object> newList = Collections.emptyList();
        List<Object> nameList = Collections.emptyList();
        Log.d("MOOOPOOOOOD", "SHOULD BE HERE NEXT");
        if (mood.trim().equals("sad")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.sad_albums));
            nameList = Arrays.asList(getResources().getStringArray(R.array.sad_albums_names));
        }
        else if (mood.trim().equals("workout")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.workout_albums));
            nameList = Arrays.asList(getResources().getStringArray(R.array.workout_albums_names));
        }
        else if (mood.trim().equals("happy")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_albums));
            nameList = Arrays.asList(getResources().getStringArray(R.array.happy_albums_names));
        }
        else if (mood.trim().equals("study")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.study_albums));
            nameList = Arrays.asList(getResources().getStringArray(R.array.study_albums_names));
        }
        else if (mood.trim().equals("party")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.party_albums));
            nameList = Arrays.asList(getResources().getStringArray(R.array.party_albums_names));
        }
        else {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_albums));
            nameList = Arrays.asList(getResources().getStringArray(R.array.happy_albums_names));
        }
        List<AlbumViewModel> simpleViewModelList = new ArrayList<>();
        //String songName = "    " + getString(R.string.party_song_still_brazy_name);

        for (int i = 0; i < newList.size(); i++) {
            simpleViewModelList.add(new AlbumViewModel(String.format(Locale.US, (String) newList.get(i))));
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
    public void onAlbumItemClick(String albumUri) {
        Log.d("SongActivity", "CLICKED IN ALBUM ACITIVYT!!!!!");

        Intent intent = new Intent(this, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_DATA, albumUri);
        startActivity(intent);
    }
}