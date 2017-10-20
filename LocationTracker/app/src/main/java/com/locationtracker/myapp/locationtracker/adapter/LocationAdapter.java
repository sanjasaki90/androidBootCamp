package com.locationtracker.myapp.locationtracker.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View result = null;

        Location location = getItem(position);

        ArrayList<TextView> locationsView = new ArrayList<>();

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());

        }



        return result;
    }



}
