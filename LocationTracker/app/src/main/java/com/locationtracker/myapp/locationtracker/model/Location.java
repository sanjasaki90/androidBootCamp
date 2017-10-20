package com.locationtracker.myapp.locationtracker.model;

/**
 * Created by sanja.jovanovic on 10/18/2017.
 */

public class Location {

    public static final String TRACK_ID = "Track_id";
    public static final String LATITUDE_KEY = "Latitude";
    public static final String LONGITUDE_KEY = "Longitude";
    public static final String ALTITUDE_KEY = "Altitude";
    public static final String SPEED_KEY = "Speed";

    public static final int MIN_LATITUDE = -90;
    public static final int MAX_LATITUDE = 90;
    public static final int MIN_LONGITUDE = -180;
    public static final int MAX_LONGITUDE = 180;

    private long id;
    private double latitude;
    private double longitude;
    private double altitude;
    private double speed;

    public Location(long id, double latitude, double longitude, double altitude, double speed) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
