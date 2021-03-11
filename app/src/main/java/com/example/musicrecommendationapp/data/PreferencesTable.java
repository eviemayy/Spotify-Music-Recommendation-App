package com.example.musicrecommendationapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "preferences")
public class PreferencesTable implements Serializable {
    @PrimaryKey
    public String songName;

    public String artistName;

    public String albumName;


    public PreferencesTable(String songName, String artistName, String albumName) {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
    }
}
