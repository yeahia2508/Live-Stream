package com.dhakadigital.tdd.livestream;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class MainActivity extends YouTubeBaseActivity  implements
        YouTubePlayer.OnInitializedListener,
        YouTubePlayer.OnFullscreenListener,
        YouTubePlayer.PlayerStateChangeListener{
    private static final String VIDEO_ID = "MTBOptDSVhQ";
    public static final int RECOVERY_DIALOG_REQUEST = 1;

    @SuppressLint("InlinedApi")
    private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;

    @SuppressLint("InlinedApi")
    private static final int LANDSCAPE_ORIENTATION = Build.VERSION.SDK_INT < 9
            ? ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            : ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

    private YouTubePlayerFragment youTubePlayerFragment;
    private FloatingActionButton fab;
    private String mVideoId = null;
    private YouTubePlayer mPlayer = null;
    private boolean mAutoRotation = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(Constants.DEVELOPER_KEY, this);
        initListener();
    }

    private void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RECOVERY_DIALOG_REQUEST){
            //Retry initialization if user performed a recovery action
            getYoutubePlayerProvider().initialize(Constants.DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYoutubePlayerProvider(){
        return (YouTubePlayer.Provider) findViewById(R.id.youtubeplayerfragment);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,RECOVERY_DIALOG_REQUEST).show();
        }else {
            String errorMessage = String.format(
                    "There was an error initializing the Youtube Player (%1$s)",
                    youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFullscreen(boolean b) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}
