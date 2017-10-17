package com.locationtracker.myapp.locationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyTracksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tracks);
    }


    public void NewTrack(View view){
        Intent intent = new Intent(this, NewTrackActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
