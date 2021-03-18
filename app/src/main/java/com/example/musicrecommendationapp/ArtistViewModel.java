package com.example.musicrecommendationapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class ArtistViewModel extends ViewModel {

    private String artistUri;
    private String artistName;

    public ArtistViewModel(@NonNull final String artistUri, String artistName) {
        this.artistUri = artistUri;
        this.artistName = artistName;
    }

    @NonNull
    public String getArtistUri() {
        return artistUri;
    }
    public String getArtistName() { return artistName; }

    public void setArtistUri(@NonNull final String simpleText) {
        this.artistUri = simpleText;
    }
    public void setArtistName(final String name) { this.artistName = name; }

}
