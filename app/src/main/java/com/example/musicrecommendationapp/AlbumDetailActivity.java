package com.example.musicrecommendationapp;

import android.app.Activity;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

public class AlbumDetailActivity extends AppCompatActivity {

    private static final String TAG = AlbumDetailActivity.class.getSimpleName();

    public static final String EXTRA_ALBUM_URI = "AlbumDetailActivity.AlbumUri";
    public static final String EXTRA_ALBUM_NAME = "AlbumDetailActivity.AlbumName";
    public static final String EXTRA_ALBUM_DATA = "AlbumDetailActivity.AlbumData";

    private SharedPreferences sharedPreferences;
  
    private String albumName;
    private String albumArtist;
    private String albumUri;
    private ImageUri imageUri;

    private static final String CLIENT_ID = "0e3c1ff267e240949fdff12722057eca";
    private static final String REDIRECT_URI = "http://com.example.musicrecommendationapp://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    ImageView albumArtImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_ALBUM_URI) && intent.hasExtra(EXTRA_ALBUM_NAME)){
            this.albumUri = (String)intent.getSerializableExtra(EXTRA_ALBUM_URI);
            this.albumName = (String)intent.getSerializableExtra(EXTRA_ALBUM_NAME);
            Log.d(TAG, "Album name: " + albumName
                    + " Album uri: " + albumUri
                    + " Album artist: " + albumArtist);

            TextView artistTV = findViewById(R.id.tv_album_artist);
            TextView albumNameTV = findViewById(R.id.tv_album_title);
            albumNameTV.setText(albumName);
            albumArtImageView = findViewById(R.id.iv_album_art);

            albumNameTV.setText(albumName);
            artistTV.setText(albumArtist);
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
            Log.d(TAG, "AlbumDetailActivity: IsSpotifyInstalled: " + isSpotifyInstalled);

        }

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
        mSpotifyAppRemote.getPlayerApi().play(albumUri);

        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("MainActivity", track.name + " by " + track.artist.name);
                        this.albumName = track.name;
                        this.albumArtist = track.artist.name;
                        this.imageUri = track.imageUri;
                        mSpotifyAppRemote.getImagesApi().getImage(imageUri).setResultCallback(
                                bitmap -> {
                                    albumArtImageView.setImageBitmap(bitmap);
                                    Log.d(TAG, bitmap.toString());
                                }
                        );
                        artistTV.setText(albumArtist);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_share:
                shareAlbum();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareAlbum(){
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mood = sharedPreferences.getString("mood", "Happy");
        if(this.albumUri != null){
            String shareText;
            shareText = getString(R.string.share_album_text, mood, this.albumName, this.albumArtist);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            intent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(intent, null);
            startActivity(chooserIntent);
        }
    }


}
