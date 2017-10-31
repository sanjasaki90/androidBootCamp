package com.locationtracker.myapp.locationtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.locationtracker.myapp.locationtracker.adapter.LocationAdapter;
import com.locationtracker.myapp.locationtracker.database.LocationHelper;
import com.locationtracker.myapp.locationtracker.model.Location;
import com.locationtracker.myapp.locationtracker.model.Track;

import java.util.List;

public class TrackDetailsActivity extends AppCompatActivity {

    private Track track;
    private long id;

    private TextView trackTitle;
    private TextView descriptionTitle;
    private ListView locationsList;
    private TextView noLocations;
    private Button addLocation;
    private LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        trackTitle = (TextView) findViewById(R.id.myTracksDetailsTitle);
        descriptionTitle = (TextView) findViewById(R.id.textViewDescription);
        locationsList = (ListView) findViewById(R.id.listLocations);
        noLocations = (TextView) findViewById(R.id.no_locations);
        addLocation = (Button) findViewById(R.id.addLocation);

        Intent getIntent = getIntent();
        track = new Track();
        if(getIntent.hasExtra(Track.TRACK_ID)) {
            id = getIntent.getIntExtra(Track.TRACK_ID, 0);
            // track.setId(id);
        }//else{
            //SharedPreferences sharedPreferences = this.getSharedPreferences(NewTrackActivity.NEW_TRACK_FILE_KEY,MODE_PRIVATE);
            //if(sharedPreferences != null) {
            //    track.setName(sharedPreferences.getString(Track.NAME,""));
           //     track.setDescription(sharedPreferences.getString(Track.DESCRIPTION, ""));
           // }
       // }
        else if(getIntent.hasExtra(Track.NAME) && getIntent.hasExtra(Track.DESCRIPTION)){
           track.setName(getIntent.getStringExtra(Track.NAME));
           track.setDescription(getIntent.getStringExtra(Track.DESCRIPTION));
        }
        addLocation.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart(){
        super.onStart();
      //  Location loc = new Location(1, 50, 59, 59,8);
      //  List<Location> l = new ArrayList<>();
       // l.add(0,loc);
       // track.setLocations(l);

        if(id > 0){
            track = LocationHelper.getInstance(this).getTrackById(id);
            List<Location> locations = LocationHelper.getInstance(this).getLocationsByTrackId(id);
            track.setLocations(locations);
        }else{
            LocationHelper.getInstance(this).save(track);
        }

        trackTitle.setText(track.getName());
        descriptionTitle.setText(track.getDescription());
        if(track.getLocations().size() > 0){
            locationAdapter = new LocationAdapter(getApplicationContext(),track.getLocations());
            locationsList.setAdapter(locationAdapter);
            noLocations.setVisibility(View.GONE);
            locationsList.setVisibility(View.VISIBLE);
        }else{
            locationsList.setVisibility(View.GONE);
            noLocations.setVisibility(View.VISIBLE);
        }
    }
    public void onDone(View view){
        if(addLocation.getVisibility() == View.VISIBLE){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.track_details_dialog_title));
            builder.setMessage(getString(R.string.track_details_dialog_message));
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.track_details_dialog_yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    TrackDetailsActivity.this.finish();
                }
            });
            builder.setNegativeButton(R.string.track_details_dialog_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            finish();
        }
    }
    public void addLocation(View view){
        Intent intent = new Intent(this, AddLocationActivity.class);
        intent.putExtra(Track.TRACK_ID, track.getId());
        startActivity(intent);
    }
    @Override
    protected void onDestroy(){
        LocationHelper.getInstance(this).closeDB();
        super.onDestroy();
    }
}