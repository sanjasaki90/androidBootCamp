package com.locationtracker.myapp.locationtracker.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by sanja.jovanovic on 10/25/2017.
 */

public class LocationsContractClass {

    public static final String DATABASE_NAME = "Location_Tracker.db";
    public static final int DATABASE_VERSION = 1;

    public static class Tracks implements BaseColumns {

        public static final String TABLE_NAME = "Tracks";

        public static final String NAME_COLUMN = "name";
        public static final String DESCRIPTION_COLUMN = "description";

        public static final int ID_COLUMN_ORDER = 0;
        public static final int NAME_COLUMN_ORDER = 1;
        public static final int DESCRIPTION_COLUMN_ORDER = 2;

        public static final String CREATE_TABLE = "create table" + " " + TABLE_NAME +
                "(" + _ID + " " + "integer primary key autoincrement,"
                   + NAME_COLUMN + " " + "string not null,"
                   + DESCRIPTION_COLUMN + " " + "string not null );";

        public static final String[] TRACK_PROJECTION = {
                BaseColumns._ID,
                NAME_COLUMN,
                DESCRIPTION_COLUMN
        };
        public static final String TRACK_SELECTION_BY_ID = _ID + "=?";

        public static final void onCreate(SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
    }
    public static class Locations implements BaseColumns {

        public static final String TABLE_NAME = "Locations";

        public static final String TRACK_ID_COLUMN  = "Track_id";
        public static final String LATITUDE_COLUMN = "Latitude";
        public static final String LONGITUDE_COLUMN = "Longitude";
        public static final String ALTITUDE_COLUMN = "Altitude";
        public static final String SPEED_COLUMN = "Speed";

        public static final int ID_COLUMN_ORDER = 0;
        public static final int TRACK_ID_COLUMN_ORDER = 1;
        public static final int LATITUDE_COLUMN_ORDER = 2;
        public static final int LONGITUDE_COLUMN_ORDER = 3;
        public static final int ALTITUDE_COLUMN_ORDER = 4;
        public static final int SPEED_COLUMN_ORDER = 5;

        public static final String CREATE_TABLE = "create table" + " " + TABLE_NAME +
               "(" + _ID + " integer primary key autoincrement," +
                     TRACK_ID_COLUMN + " " +"string not null," +
                     LATITUDE_COLUMN + " " + "string not null," +
                     LONGITUDE_COLUMN + " " +  "string not null," +
                     ALTITUDE_COLUMN + " " + "string not null," +
                     SPEED_COLUMN + " " + "string not null );";

        public static final String[] LOCATIONS_PROJECTION = {
                BaseColumns._ID,
                TRACK_ID_COLUMN,
                LATITUDE_COLUMN,
                LONGITUDE_COLUMN,
                ALTITUDE_COLUMN,
                SPEED_COLUMN
        };

        public static final String LOCATIONS_SELECTION_BY_TRACK_ID = TRACK_ID_COLUMN + "=?";

        public static final void onCreate(SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
    }
}