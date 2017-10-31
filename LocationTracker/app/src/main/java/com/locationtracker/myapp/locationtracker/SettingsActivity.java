package com.locationtracker.myapp.locationtracker;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.locationtracker.myapp.locationtracker.model.Location;

public class SettingsActivity extends AppCompatActivity {

    private EditText editSamplingRate;
    private EditText editSetSpeed;
    private Spinner spinnerHomeCities;
    private TextView fieldLabelSetLatitude;
    private TextView fieldLabelSetLongitude;
    private TextView textViewHomeCityAltitude;

    public static final String SETTINGS_PREFERENCES_FILE_KEY = "Settings";
    public static final String SAMPLING_RATE_KEY= "Sampling_rate";
    public static final String CITY_INDEX_KEY = "City_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editSamplingRate = (EditText) findViewById(R.id.editSamplingRate);
        editSetSpeed = (EditText) findViewById(R.id.editSpeed);
        spinnerHomeCities = (Spinner) findViewById(R.id.spinnerHomeCities);
        fieldLabelSetLatitude = (TextView) findViewById(R.id.fieldLabelSetLatitude);
        fieldLabelSetLongitude = (TextView) findViewById(R.id.fieldLabelSetLongitude);
        textViewHomeCityAltitude =(TextView) findViewById(R.id.textViewHomeCityAltitude);

        SharedPreferences sharedPreferences = this.getSharedPreferences(SETTINGS_PREFERENCES_FILE_KEY, MODE_PRIVATE);

        editSamplingRate.setText(sharedPreferences.getString(SAMPLING_RATE_KEY, "0"));
        editSetSpeed.setText(sharedPreferences.getString(Location.SPEED_KEY, "0"));
        spinnerHomeCities.setSelection(sharedPreferences.getInt(CITY_INDEX_KEY, 0));

        spinnerHomeCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fieldLabelSetLatitude.setText(getString(R.string.settings_latitude,
                        getResources().getStringArray(R.array.homeCitiesLatitudes)[i]));
                fieldLabelSetLongitude.setText(getString(R.string.settings_longitude,
                        getResources().getStringArray(R.array.homeCitiesLongitudes)[i]));
                textViewHomeCityAltitude.setText(getString(R.string.settings_altitude,
                        getResources().getStringArray(R.array.homeCitiesAltitudes)[i]));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    public void settingsDone(View view){
        boolean isError = false;
        if(editSamplingRate.getText().toString().equalsIgnoreCase("")){
            isError = true;
            editSamplingRate.setError(getString(R.string.new_track_error_field_format,
                    getString(R.string.track_name_SamplingRate)));
        }
        if(editSetSpeed.getText().toString().equalsIgnoreCase("")){
            isError = true;
            editSetSpeed.setError(getString(R.string.new_track_error_field_format,
                    getString(R.string.Speed)));
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
            return;
        }

        SharedPreferences sharedPreferences = this.getSharedPreferences(SETTINGS_PREFERENCES_FILE_KEY, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        int cityIndex = spinnerHomeCities.getSelectedItemPosition();
        editor.putString(Location.LONGITUDE_KEY, getResources().getStringArray(R.array.homeCitiesLongitudes)[cityIndex]);
        editor.putString(Location.LATITUDE_KEY, getResources().getStringArray(R.array.homeCitiesLatitudes)[cityIndex]);
        editor.putString(Location.ALTITUDE_KEY, getResources().getStringArray(R.array.homeCitiesAltitudes)[cityIndex]);
        editor.putInt(CITY_INDEX_KEY, cityIndex);
        editor.putString(SAMPLING_RATE_KEY, editSamplingRate.getText().toString());
        editor.putString(Location.SPEED_KEY, editSetSpeed.getText().toString());

        editor.commit();

        finish();
    }
}