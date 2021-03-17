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
        implements AlbumAdapter.OnAlbumItemClickListener {


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

        for (int i = 0; i < newList.size(); i++) {
            simpleViewModelList.add(new AlbumViewModel(String.format(Locale.US, (String) newList.get(i)),(String) nameList.get(i)));
        }

        return simpleViewModelList;
    }


    @Override
    public void onAlbumItemClick(String albumUri, String albumName) {
        Log.d("SongActivity", "CLICKED IN ALBUM ACITIVYT!!!!!");

        Intent intent = new Intent(this, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_URI, albumUri);
        intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_NAME, albumName);
        startActivity(intent);
    }
}