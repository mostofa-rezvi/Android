package com.travel.travelguide.domain;

public class Location {

    private int Id;
    private String Location;

    public Location(int id, String location) {
        Id = id;
        Location = location;
    }

    @Override
    public String toString() {
        return Location;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
