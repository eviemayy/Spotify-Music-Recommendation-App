package com.example.musicrecommendationapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        RecyclerView albumsRV = findViewById(R.id.rv_albums);
        albumsRV.setLayoutManager(new LinearLayoutManager(this));
        albumsRV.setHasFixedSize(true);
    }
}