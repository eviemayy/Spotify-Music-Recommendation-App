package com.example.musicrecommendationapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class AlbumViewModel extends ViewModel {

    private String simpleText;

    public AlbumViewModel(@NonNull final String simpleText) {
        setSimpleText(simpleText);
    }

    @NonNull
    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(@NonNull final String simpleText) {
        this.simpleText = simpleText;
    }

}
