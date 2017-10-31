package com.locationtracker.myapp.locationtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.locationtracker.myapp.locationtracker.database.LocationHelper;
import com.locationtracker.myapp.locationtracker.model.Location;
import com.locationtracker.myapp.locationtracker.model.Track;

public class AddLocationActivity extends AppCompatActivity {

    private EditText editLongitude;
    private EditText editLatitude;
    private EditText editAltitude;
    private EditText editSpeed;

    private Long trackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        editLongitude = (EditText) findViewById(R.id.editLongitude);
        editLatitude = (EditText) findViewById(R.id.editLatitude);
        editAltitude = (EditText) findViewById(R.id.editAltitude);
        editSpeed = (EditText) findViewById(R.id.editSpeed);

        Intent intent = getIntent();
        if(intent.hasExtra(Track.TRACK_ID)) {
            trackId = intent.getLongExtra(Track.TRACK_ID, 0);
        }
        SharedPreferences sharedPreferences = this.getSharedPreferences(SettingsActivity.SETTINGS_PREFERENCES_FILE_KEY, MODE_PRIVATE);

        editSpeed.setText(sharedPreferences.getString(Location.SPEED_KEY,"0"));

        int index = sharedPreferences.getInt(SettingsActivity.CITY_INDEX_KEY, 0);
        editLongitude.setText(sharedPreferences.getString(Location.LONGITUDE_KEY, getResources().getStringArray(R.array.homeCitiesLongitudes)[index]));
        editLatitude.setText(sharedPreferences.getString(Location.LATITUDE_KEY, getResources().getStringArray(R.array.homeCitiesLatitudes)[index]));
        editAltitude.setText(sharedPreferences.getString(Location.ALTITUDE_KEY, getResources().getStringArray(R.array.homeCitiesAltitudes)[index]));
    }

    public void addDone(View view){
        boolean isError = false;

        if(editLongitude.getText().toString().equalsIgnoreCase("")){
            isError = true;
            editLongitude.setError(getString(R.string.new_track_error_field_format, getString(R.string.longitude)));
        }if(editLatitude.getText().toString().equalsIgnoreCase("")){
            isError = true;
            editLatitude.setError(getString(R.string.new_track_error_field_format, getString(R.string.latitude)));
        }if(editAltitude.getText().toString().equalsIgnoreCase("")){
            isError = true;
            editAltitude.setError(getString(R.string.new_track_error_field_format, getString(R.string.altitude)));
        }
        if(!isError){
            if(Double.parseDouble(editLongitude.getText().toString()) < Location.MIN_LONGITUDE ||
                    Double.parseDouble(editLongitude.getText().toString()) > Location.MAX_LONGITUDE){
                isError = true;
                editLongitude.setError(getString(R.string.add_location_error_field_value_format, getString(R.string.longitude),
                        Location.MIN_LONGITUDE, Location.MAX_LONGITUDE));
            } if(Double.parseDouble(editLatitude.getText().toString()) < Location.MIN_LATITUDE ||
                  Double.parseDouble(editLatitude.getText().toString()) > Location.MAX_LATITUDE){
                isError = true;
                editLatitude.setError(getString(R.string.add_location_error_field_value_format, getString(R.string.latitude),
                        Location.MIN_LATITUDE, Location.MAX_LATITUDE));
            }
        }
        if(isError){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.invalid_input));
            builder.setMessage(getString(R.string.enter_correct_val));
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            //long id, double latitude, double longitude, double altitude, double speed)
            Location location = new Location(trackId, Double.parseDouble(editLongitude.getText().toString()), Double.parseDouble(editLatitude.getText().toString()),
                    Double.parseDouble(editAltitude.getText().toString()), 0);
            LocationHelper.getInstance(this).saveLocation(location);
            finish();
        }
    }
    @Override
    protected void onDestroy(){
        LocationHelper.getInstance(this).closeDB();
        super.onDestroy();
    }
}