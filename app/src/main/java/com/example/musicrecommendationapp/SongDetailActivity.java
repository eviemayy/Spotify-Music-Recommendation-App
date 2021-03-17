package com.example.musicrecommendationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.Image;
import com.spotify.protocol.types.ImageUri;
import com.spotify.protocol.types.Track;

import org.w3c.dom.Text;

public class SongDetailActivity extends AppCompatActivity {

    private static final String TAG = SongDetailActivity.class.getSimpleName();
    public static final String EXTRA_SONG_URI = "SongDetailActivity.SongUri";
    public static final String EXTRA_SONG_NAME = "SongDetailActivity.SongName";

    private String songName;
    private String songArtist;
    private String songUri;
    private ImageUri imageUri;

    private static final String CLIENT_ID = "0e3c1ff267e240949fdff12722057eca";
    private static final String REDIRECT_URI = "http://com.example.musicrecommendationapp://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    ImageView albumArtImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_SONG_URI) && intent.hasExtra(EXTRA_SONG_NAME)){
            this.songUri = (String)intent.getSerializableExtra(EXTRA_SONG_URI);
            this.songName = (String)intent.getSerializableExtra(EXTRA_SONG_NAME);

            Log.d(TAG, "Song name: " + songName
                    + " Song uri: " + songUri
                    + " Song artist: " + songArtist);
        }

        // Set text
        TextView songTitleTV = findViewById(R.id.tv_song_title);
        TextView artistTV = findViewById(R.id.tv_song_artist);
        albumArtImageView = findViewById(R.id.iv_album_art);

        songTitleTV.setText(songName);
        artistTV.setText(songArtist);
        albumArtImageView.setImageResource(R.drawable.moodify_logo);


        //  Is the spotify app installed on the device?
        PackageManager pm = getApplicationContext().getPackageManager();
        boolean isSpotifyInstalled;
        try {
            pm.getPackageInfo("com.spotify.music", 0);
            isSpotifyInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            isSpotifyInstalled = false;
        }
        Log.d(TAG, "SongDetailActivity: IsSpotifyInstalled: " + isSpotifyInstalled);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set the connection parameters
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d(TAG, "Connected! Yay!");

                        // Now you can start interacting with App Remote
                        connected();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e(TAG, "Error message: " + throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });


        }

        private void connected(){

            TextView artistTV = findViewById(R.id.tv_song_artist);

            // Play a playlist
            Log.d(TAG, "CONNECTED FUNCTION: ");
            mSpotifyAppRemote.getPlayerApi().play(songUri);

            // Subscribe to PlayerState
            mSpotifyAppRemote.getPlayerApi()
                    .subscribeToPlayerState()
                    .setEventCallback(playerState -> {
                        final Track track = playerState.track;
                        if (track != null) {
                            Log.d("MainActivity", track.name + " by " + track.artist.name);
                            this.songName = track.name;
                            this.songArtist = track.artist.name;
                            this.imageUri = track.imageUri;
                            mSpotifyAppRemote.getImagesApi().getImage(imageUri).setResultCallback(
                                    bitmap -> {
                                        albumArtImageView.setImageBitmap(bitmap);
                                        Log.d(TAG, bitmap.toString());
                                    }
                            );
                            artistTV.setText(songArtist);
                        }
                    });
        }


    @Override
    protected void onStop() {

        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    private void onDisconnected(){
        albumArtImageView.setImageResource(R.drawable.moodify_logo);
    }
}
