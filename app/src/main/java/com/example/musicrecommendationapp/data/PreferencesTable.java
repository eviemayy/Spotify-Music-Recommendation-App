package com.example.musicrecommendationapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "preferences")
public class PreferencesTable implements Serializable {
    @PrimaryKey
    @NonNull
    public String songName;

    public String artistName;

    public String albumName;


    public PreferencesTable(String songName, String artistName, String albumName) {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
    }
}
