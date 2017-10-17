package com.locationtracker.myapp.locationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewTrackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);
    }

    public void StartTrack(View view){
        Intent intent = new Intent(this, TrackDetailsActivity.class);
        startActivity(intent);
    }
}
