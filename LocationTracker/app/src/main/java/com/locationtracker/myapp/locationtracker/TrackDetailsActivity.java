package com.locationtracker.myapp.locationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TrackDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);
    }


    public void addLocation(View view){
        Intent intent = new Intent(this, AddLocationActivity.class);
        startActivity(intent);
    }
}
