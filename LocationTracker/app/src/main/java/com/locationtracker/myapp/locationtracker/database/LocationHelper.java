package com.locationtracker.myapp.locationtracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.locationtracker.myapp.locationtracker.model.Location;
import com.locationtracker.myapp.locationtracker.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanja.jovanovic on 10/26/2017.
 */

public class LocationHelper extends SQLiteOpenHelper {

    private static LocationHelper locationHelper;

    public LocationHelper(Context context) {
        super(context, LocationsContractClass.DATABASE_NAME, null, LocationsContractClass.DATABASE_VERSION);
    }

    public static LocationHelper getInstance(Context context){
            if(locationHelper == null){
                locationHelper = new LocationHelper(context);
            }
        return locationHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        LocationsContractClass.Tracks.onCreate(sqLiteDatabase);
        LocationsContractClass.Locations.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public long save(Track track){

        ContentValues values = new ContentValues();
        values.put(LocationsContractClass.Tracks.NAME_COLUMN, track.getName());
        values.put(LocationsContractClass.Tracks.DESCRIPTION_COLUMN, track.getDescription());

        long trackId = getWritableDatabase().insert(LocationsContractClass.Tracks.TABLE_NAME, null, values);

        track.setId(trackId);
        return trackId;
    }
    public void saveLocation(Location location){
        ContentValues values = new ContentValues();
       // values.put(LocationsContractClass.Locations._ID, location.getId());
        values.put(LocationsContractClass.Locations.TRACK_ID_COLUMN, location.getId());
        values.put(LocationsContractClass.Locations.LATITUDE_COLUMN, location.getLatitude());
        values.put(LocationsContractClass.Locations.LONGITUDE_COLUMN, location.getLongitude());
        values.put(LocationsContractClass.Locations.ALTITUDE_COLUMN, location.getAltitude());
        values.put(LocationsContractClass.Locations.SPEED_COLUMN, location.getSpeed());

        long locationId = getWritableDatabase().insert(LocationsContractClass.Locations.TABLE_NAME, null, values);

        location.setId(locationId);
     }
     public List<Track> allTracks(){

         Cursor cursor = getReadableDatabase().query(
                 LocationsContractClass.Tracks.TABLE_NAME,
                 LocationsContractClass.Tracks.TRACK_PROJECTION,
                 null,null,null,null,null);

         List<Track> allTracks = new ArrayList<>();
         while (cursor.moveToNext()){
             Track track = new Track();
             track.setId(cursor.getLong(LocationsContractClass.Tracks.ID_COLUMN_ORDER));
             track.setName(cursor.getString(LocationsContractClass.Tracks.NAME_COLUMN_ORDER));
             track.setDescription(cursor.getString(LocationsContractClass.Tracks.DESCRIPTION_COLUMN_ORDER));

             allTracks.add(track);
         }
         cursor.close();
         return allTracks;
     }
     public Track getTrackById(long id){
         Cursor cursor = getReadableDatabase().
                 query(LocationsContractClass.Tracks.TABLE_NAME,
                       LocationsContractClass.Tracks.TRACK_PROJECTION,
                         LocationsContractClass.Tracks.TRACK_SELECTION_BY_ID,
                         new String[] {"" + id}, null, null, null);

         Track track = new Track();
         while (cursor.moveToNext()) {
             track.setId(cursor.getLong(LocationsContractClass.Tracks.ID_COLUMN_ORDER));
             track.setName(cursor.getString(LocationsContractClass.Tracks.NAME_COLUMN_ORDER));
             track.setDescription(cursor.getString(LocationsContractClass.Tracks.DESCRIPTION_COLUMN_ORDER));
         }
         cursor.close();
         return track;
     }
     public List<Location> getLocationsByTrackId(long id){
         Cursor cursor = getReadableDatabase().query(LocationsContractClass.Locations.TABLE_NAME,
                 LocationsContractClass.Locations.LOCATIONS_PROJECTION,
                 LocationsContractClass.Locations.LOCATIONS_SELECTION_BY_TRACK_ID,
                 new String[] {"" + id},null, null, null);

         List<Location> locations = new ArrayList<>();
         while (cursor.moveToNext()){
             //long id, double latitude, double longitude, double altitude, double speed
             Location location = new Location(id, Double.parseDouble(cursor.getString(LocationsContractClass.Locations.LATITUDE_COLUMN_ORDER)),
                     Double.parseDouble(cursor.getString(LocationsContractClass.Locations.LONGITUDE_COLUMN_ORDER)), Double.parseDouble(cursor.getString(LocationsContractClass.Locations.ALTITUDE_COLUMN_ORDER)),
                             Double.parseDouble(cursor.getString(LocationsContractClass.Locations.SPEED_COLUMN_ORDER)));
             locations.add(location);
         }
         cursor.close();
         return locations;
     }
    public void closeDB(){
         close();
    }
}