package com.example.musicrecommendationapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface preferencesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PreferencesTable savedKey);

    @Delete
    void delete(PreferencesTable savedKey);


    @Query("SELECT * FROM preferences")
    LiveData<List<PreferencesTable>> getAllPreferences();

    /*@Query("SELECT songName FROM preferences")
    LiveData<List<PreferencesTable>> getSongNames();

    @Query("SELECT artistName FROM preferences")
    LiveData<List<PreferencesTable>> getArtistNames();

    @Query("SELECT albumName FROM preferences")
    LiveData<List<PreferencesTable>> getAlbumNames();*/
}
