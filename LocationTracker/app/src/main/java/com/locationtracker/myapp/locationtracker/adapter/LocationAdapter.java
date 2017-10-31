package com.locationtracker.myapp.locationtracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.locationtracker.myapp.locationtracker.R;
import com.locationtracker.myapp.locationtracker.model.Location;

/**
 * Created by sanja.jovanovic on 10/20/2017.
 */

public class LocationAdapter extends ArrayAdapter<Location>{

    Context context;
    TextView latitude;
    TextView longitude;
    TextView altitude;
    TextView index;

    public LocationAdapter(Context context, List<Location> locations){
        super(context, R.layout.location_in_list, locations);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View result = null;

        Location location = getItem(position);

        ArrayList<TextView> locationsView = new ArrayList<>();

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.location_in_list, parent ,false);

            latitude = convertView.findViewById(R.id.lat);
            longitude =  convertView.findViewById(R.id.longi);
            altitude = convertView.findViewById(R.id.alti);
            index = convertView.findViewById(R.id.noOfLocation);

            locationsView.add(0, index);
            locationsView.add(1, latitude);
            locationsView.add(2, longitude);
            locationsView.add(3, altitude);

            convertView.setTag(locationsView);
        }else{
            locationsView = (ArrayList<TextView>) convertView.getTag();
        }
        result = convertView;

        latitude.setText(context.getString(R.string.settings_latitude_format, location.getLatitude()));
        longitude.setText(context.getString(R.string.settings_longitude_format, location.getLongitude()));
        altitude.setText(context.getString(R.string.settings_altitude_format, location.getAltitude()));
        index.setText(Integer.toString(position +1));

        return result;
    }
}