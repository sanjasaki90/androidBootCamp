package com.locationtracker.myapp.locationtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.locationtracker.myapp.locationtracker.adapter.TrackAdapter;
import com.locationtracker.myapp.locationtracker.model.Track;

public class MyTracksActivity extends AppCompatActivity {

    private TextView noTracks;
    private ListView listTracks;
    private List<Track> tracks = new ArrayList<>();
    private TrackAdapter trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tracks);

        noTracks = (TextView) findViewById(R.id.no_tracks);
        listTracks = (ListView) findViewById(R.id.listTracks);

    }
    @Override
    protected void onStart(){
        super.onStart();

        Track track = new Track();
        track.setId(1);
        track.setDescription("Ovo je test za desc");
        track.setName("Ovo je test za name");
        tracks.add(track);

        trackAdapter = new TrackAdapter(getApplicationContext(), tracks);
        listTracks.setAdapter(trackAdapter);

        listTracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyTracksActivity.this, TrackDetailsActivity.class);
                intent.putExtra(Track.TRACK_ID, i+1);
                startActivity(intent);
            }
        });

        if(tracks.size() > 0) {
            listTracks.setVisibility(View.VISIBLE);
            noTracks.setVisibility(View.GONE);
        } else {
            listTracks.setVisibility(View.GONE);
            noTracks.setVisibility(View.VISIBLE);
        }
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
