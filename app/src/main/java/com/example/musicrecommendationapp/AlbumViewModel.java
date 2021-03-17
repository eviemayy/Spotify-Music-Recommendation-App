package com.example.musicrecommendationapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class AlbumViewModel extends ViewModel {

    private String albumUri;
    private String albumName;
    private String albumArtist;
    private String albumArtUri;


    public AlbumViewModel(@NonNull final String albumUri, String albumName) {
        this.albumUri = albumUri;
        this.albumName = albumName;
    }

    //get the album uri
    @NonNull
    public String getAlbumUri() {
        return albumUri;
    }

    //get the album name
    @NonNull
    public String getAlbumName() {
        return albumName;
    }

    //get the album artist
    @NonNull
    public String getAlbumArtist() {
        return albumArtist;
    }

    //get the album art uri
    @NonNull
    public String getAlbumArtUri() {
        return albumArtUri;
    }


    //set the song uri
    public void setAlbumUri(@NonNull final String albumUri) {
        this.albumUri = albumUri;
    }

}
