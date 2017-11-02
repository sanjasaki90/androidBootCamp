package com.locationtracker.myapp.locationtracker.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.locationtracker.myapp.locationtracker.database.LocationHelper;
import com.locationtracker.myapp.locationtracker.database.LocationsContractClass;

/**
 * Created by sanja.jovanovic on 10/31/2017.
 */

public class LocationsProvider extends ContentProvider {

    private static final UriMatcher sURIMatcher = new UriMatcher( UriMatcher.NO_MATCH);

    SQLiteOpenHelper mDbHelper;
    public LocationsProvider(){}

    @Override
    public boolean onCreate(){
        mDbHelper = LocationHelper.getInstance(getContext());

        sURIMatcher.addURI(LocationsContractClass.AUTHORITY, "/tracks", 1);
        sURIMatcher.addURI(LocationsContractClass.AUTHORITY, "/tracks" + "#", + 2);
        sURIMatcher.addURI(LocationsContractClass.AUTHORITY, "/locations", 3);
        sURIMatcher.addURI(LocationsContractClass.AUTHORITY, "/locations" + "#", 4);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
       Cursor resultCursor = null;
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        switch (sURIMatcher.match(uri)) {
            case 1:
            case 2:
                resultCursor = database.query(LocationsContractClass.Tracks.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, null, null);
                break;
            case 3:
            case 4:
                resultCursor = database.query(LocationsContractClass.Locations.TABLE_NAME, projection,
                        selection, selectionArgs, null, null, null, null);
            default:
                break;
        }
        return resultCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        Uri resultUri = null;
        long id =0;
        switch (sURIMatcher.match(uri)){
            case 1:
                id = database.insert(LocationsContractClass.Tracks.TABLE_NAME,null, values);
                break;
            case 2:
            case 3:
                id= database.insert(LocationsContractClass.Locations.TABLE_NAME, null, values);
                break;
            case 4:
                break;
            default:
                break;
        }
        resultUri = ContentUris.withAppendedId(uri, id);
        return resultUri;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        throw new UnsupportedOperationException("No update!");
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        throw new UnsupportedOperationException("No delete!");
    }
}
