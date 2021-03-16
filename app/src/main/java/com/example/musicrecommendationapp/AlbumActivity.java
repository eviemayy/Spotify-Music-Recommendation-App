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

public class AlbumActivity extends AppCompatActivity {
    //private AlbumAdapter albumAdapter;
    private SongViewModel songViewModel;
    private RecyclerView albumRV;
    private SharedPreferences sharedPreferences;


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

        //this.albumAdapter = new AlbumAdapter(this.generateSimpleList(mood));
        this.albumRV = findViewById(R.id.simple_recyclerview);
        this.albumRV.setLayoutManager(new LinearLayoutManager(this));
        this.albumRV.setHasFixedSize(true);

        //this.albumRV.setAdapter(this.albumAdapter);
    }

    private List<SongViewModel> generateSimpleList(String mood) {
        List<Object> newList = Collections.emptyList();
        Log.d("MOOOPOOOOOD", "SHOULD BE HERE NEXT");
        if (mood.trim().equals("sad")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.sad_albums));
        }
        else if (mood.trim().equals("workout")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.workout_albums));
        }
        else if (mood.trim().equals("happy")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_albums));
        }
        else if (mood.trim().equals("study")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.study_albums));
        }
        else if (mood.trim().equals("party")) {
            newList = Arrays.asList(getResources().getStringArray(R.array.party_albums));
        }
        else {
            newList = Arrays.asList(getResources().getStringArray(R.array.happy_albums));
        }
        List<SongViewModel> simpleViewModelList = new ArrayList<>();
        //String songName = "    " + getString(R.string.party_song_still_brazy_name);

        for (int i = 0; i < newList.size(); i++) {
            simpleViewModelList.add(new SongViewModel(String.format(Locale.US, (String) newList.get(i))));
        }

        return simpleViewModelList;
    }
}