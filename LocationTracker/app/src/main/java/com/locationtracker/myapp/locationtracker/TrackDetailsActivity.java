package com.locationtracker.myapp.locationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TrackDetailsActivity extends AppCompatActivity {

    TextView trackTitle;
    TextView descriptionTitle;
    ListView locationsList;
    TextView noLocations;
    Button addLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        trackTitle = (TextView) findViewById(R.id.myTracksDetailsTitle);
        descriptionTitle = (TextView) findViewById(R.id.textViewDescription);
        locationsList = (ListView) findViewById(R.id.listLocations);
        noLocations = (TextView) findViewById(R.id.no_locations);
        addLocation = (Button) findViewById(R.id.addLocation);
    }


    public void addLocation(View view){
        Intent intent = new Intent(this, AddLocationActivity.class);
        startActivity(intent);
    }

}
