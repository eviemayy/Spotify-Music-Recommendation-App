package com.example.musicrecommendationapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongActivity extends AppCompatActivity {

    private SongAdapter songAdapter;
    private SongViewModel songViewModel;
    private RecyclerView songsRV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        this.songAdapter = new SongAdapter(this.generateSimpleList());
        this.songsRV = findViewById(R.id.simple_recyclerview);
        this.songsRV.setLayoutManager(new LinearLayoutManager(this));
        this.songsRV.setHasFixedSize(true);

        this.songsRV.setAdapter(this.songAdapter);
    }

    private List<SongViewModel> generateSimpleList() {
        List<SongViewModel> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            simpleViewModelList.add(new SongViewModel(String.format(Locale.US, "This is song %d", i)));
        }

        return simpleViewModelList;
    }
}
