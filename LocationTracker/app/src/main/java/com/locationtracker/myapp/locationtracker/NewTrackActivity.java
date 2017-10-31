package com.locationtracker.myapp.locationtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.locationtracker.myapp.locationtracker.model.Track;

public class NewTrackActivity extends AppCompatActivity {

    EditText editName;
    EditText editDescription;

    public static final String NEW_TRACK_FILE_KEY = "NewTrack";

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
          /* SharedPreferences sharedPreferences = this.getSharedPreferences(NEW_TRACK_FILE_KEY, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Track.NAME, editName.getText().toString());
            editor.putString(Track.DESCRIPTION, editDescription.getText().toString());
            editor.commit();*/

            Intent intent = new Intent(this, TrackDetailsActivity.class);
            intent.putExtra(Track.NAME, editName.getText().toString());
            intent.putExtra(Track.DESCRIPTION, editDescription.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}
