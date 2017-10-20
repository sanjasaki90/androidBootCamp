package com.locationtracker.myapp.locationtracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanja.jovanovic on 10/18/2017.
 */

public class Track {

    public static final String TRACK_ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";


    private long id;
    private String name;
    private String description;
    private List<Location> locations = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
