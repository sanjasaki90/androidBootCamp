package com.locationtracker.myapp.locationtracker.adapter;

/**
 * Created by sanja.jovanovic on 10/18/2017.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.locationtracker.myapp.locationtracker.R;
import com.locationtracker.myapp.locationtracker.model.Track;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class TrackAdapter extends ArrayAdapter<Track> {

    Context context;
    TextView nameInList;
    TextView descInList;

    public TrackAdapter (Context context, List<Track> tracks){
        super(context, R.layout.track_in_list, tracks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View result;

        ArrayList<TextView> elementsForTrack = new ArrayList<>();
       // HashMap<String, TextView> elementsForTrack = new HashMap<String, TextView>();

        Track track = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.track_in_list, parent, false);

            nameInList = convertView.findViewById(R.id.nameInListTrack);
            descInList = convertView.findViewById(R.id.descriptionInListTrack);

            //elementsForTrack.put("name", nameInList);
            //elementsForTrack.put("description", descInList);
            elementsForTrack.add(0, nameInList);
            elementsForTrack.add(1, descInList);

            convertView.setTag(elementsForTrack);

            result = convertView;

        } else {
            elementsForTrack = (ArrayList<TextView>) convertView.getTag();
            //nameInList.setText((CharSequence)elementsForTrack.get(0));
            //nameInList.setText((CharSequence) elementsForTrack.get("name"));
           // descInList.setText((CharSequence) elementsForTrack.get("description"));

            result = convertView;
        }
        nameInList.setText(track.getName());
        descInList.setText(track.getDescription());

        return result;
    }
}
