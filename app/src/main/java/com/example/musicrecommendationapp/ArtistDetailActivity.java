package com.example.musicrecommendationapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.types.ImageUri;
import com.spotify.protocol.types.Track;

public class ArtistDetailActivity extends AppCompatActivity {

    private static final String TAG = ArtistDetailActivity.class.getSimpleName();
    public static final String EXTRA_ARTIST_NAME = "ArtistDetailActivity.ArtistName";
    public static final String EXTRA_ARTIST_URI = "ArtistDetailActivity.ArtistUri";
    private String artistName;
    private String artistUri;
    private ImageUri imageUri;


    private SharedPreferences sharedPreferences;

    private static final String CLIENT_ID = "0e3c1ff267e240949fdff12722057eca";
    private static final String REDIRECT_URI = "http://com.example.musicrecommendationapp://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    ImageView albumArtImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_ARTIST_URI)){
            this.artistName = (String)intent.getSerializableExtra(EXTRA_ARTIST_NAME);
            this.artistUri = (String)intent.getSerializableExtra(EXTRA_ARTIST_URI);
            Log.d(TAG, "Artist name: " + artistName + "Artist URI: " + artistUri);

        }

        // Set text
        TextView artistTitleTV = findViewById(R.id.tv_artist_title);
        albumArtImageView = findViewById(R.id.iv_album_art);

        artistTitleTV.setText(artistName);
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

        TextView artistTV = findViewById(R.id.tv_album_artist);

        // Play a playlist
        Log.d(TAG, "CONNECTED FUNCTION: ");
        mSpotifyAppRemote.getPlayerApi().play(artistUri);

        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                        this.artistName = track.name;
                        this.imageUri = track.imageUri;
                        mSpotifyAppRemote.getImagesApi().getImage(imageUri).setResultCallback(
                                bitmap -> {
                                    albumArtImageView.setImageBitmap(bitmap);
                                    Log.d(TAG, bitmap.toString());
                                }
                        );
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_share:
                shareArtist();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareArtist(){
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mood = sharedPreferences.getString("mood", "Happy");
        if(this.artistUri != null){
            String shareText;
            shareText = getString(R.string.share_artist_text, mood, this.artistName);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            intent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(intent, null);
            startActivity(chooserIntent);
        }
    }


}
