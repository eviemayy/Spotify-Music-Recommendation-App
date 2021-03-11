package com.example.musicrecommendationapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        RecyclerView songsRV = findViewById(R.id.rv_songs);
        songsRV.setLayoutManager(new LinearLayoutManager(this));
        songsRV.setHasFixedSize(true);

    }
}
