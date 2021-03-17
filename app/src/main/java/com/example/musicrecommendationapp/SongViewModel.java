package com.example.musicrecommendationapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class SongViewModel extends ViewModel {

    private String songUri; //was simpleText
    private String songName;
    private String songArtist;
    private String albumArtUri;

    public SongViewModel(@NonNull final String songUri, String songName) {

        this.songUri = songUri;
        this.songName = songName;
    }

    //get the song uri
    @NonNull
    public String getSongUri() {
        return songUri;
    }

    //get the song name
    @NonNull
    public String getSongName() {
        return songName;
    }

    //get the song artist
    @NonNull
    public String getSongArtist() {
        return songArtist;
    }

    //get the album art uri
    @NonNull
    public String getAlbumArtUri() {
        return albumArtUri;
    }


    //set tthe song uri
    public void setSongUri(@NonNull final String songUri) {
        this.songUri = songUri;
    }
}
