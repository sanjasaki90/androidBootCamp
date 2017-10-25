package com.locationtracker.myapp.locationtracker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    //sh pr

    private EditText editSamplingRate;
    private EditText editSetSpeed;
    private Spinner spinnerHomeCities;
    private TextView fieldLabelSetLatitude;
    private TextView fieldLabelSetLongitude;
    private TextView textViewHomeCityAltitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editSamplingRate = (EditText) findViewById(R.id.editSamplingRate);
        editSetSpeed = (EditText) findViewById(R.id.editSetSpeed);
        spinnerHomeCities = (Spinner) findViewById(R.id.spinnerHomeCities);
        fieldLabelSetLatitude = (TextView) findViewById(R.id.fieldLabelSetLatitude);
        fieldLabelSetLongitude = (TextView) findViewById(R.id.fieldLabelSetLongitude);
        textViewHomeCityAltitude =(TextView) findViewById(R.id.textViewHomeCityAltitude);

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


        //sh pr

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



    }
}
