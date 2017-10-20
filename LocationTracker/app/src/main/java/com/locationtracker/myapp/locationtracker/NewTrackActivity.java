package com.locationtracker.myapp.locationtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.locationtracker.myapp.locationtracker.model.Track;

public class NewTrackActivity extends AppCompatActivity {


    EditText editName;
    EditText editDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);

        editName = (EditText) findViewById(R.id.editName);
        editDescription = (EditText) findViewById(R.id.editDescription);
    }

    public void StartTrack(View view){

        boolean isError = false;

        if(editName.getText().toString().equalsIgnoreCase("") == true){
            editName.setError(getString(R.string.new_track_error_field_format, getString(R.string.name)));
            isError = true;
        }
        if(editDescription.getText().toString().equalsIgnoreCase("") == true){
            editDescription.setError(getString(R.string.new_track_error_field_format, getString(R.string.track_details_description)));
            isError = true;
        }
        if(isError){
            final AlertDialog.Builder alertD = new AlertDialog.Builder(this);
            alertD.setTitle(R.string.invalid_input);
            alertD.setMessage(R.string.enter_correct_val);
            alertD.setCancelable(false);
            alertD.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertD.create();
            alertDialog.show();
            return;
        } else {
            Intent intent = new Intent(this, TrackDetailsActivity.class);
            intent.putExtra(Track.NAME, editName.getText());
            intent.putExtra(Track.DESCRIPTION, editDescription.getText());
            startActivity(intent);
        }
    }
}
