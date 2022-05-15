package com.example.teamder.models;

public class Location {
    private Double lat;
    private Double lng;

    public Location() {
    }

    public Double getLat() {
        return lat;
    }

    public Location setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public Location setLng(Double lng) {
        this.lng = lng;
        return this;
    }
}
