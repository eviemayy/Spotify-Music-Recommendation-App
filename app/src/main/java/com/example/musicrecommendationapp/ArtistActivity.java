package com.example.musicrecommendationapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArtistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        RecyclerView artistsRV = findViewById(R.id.rv_artists);
        artistsRV.setLayoutManager(new LinearLayoutManager(this));
        artistsRV.setHasFixedSize(true);
    }
}
