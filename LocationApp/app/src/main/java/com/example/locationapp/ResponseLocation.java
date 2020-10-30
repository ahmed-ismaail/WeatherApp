package com.example.locationapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLocation {
//    "name": "Place 01",
//    "lat": "30.213",
//    "lon": "32.998"

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("lat")
    @Expose
    String latitude;

    @SerializedName("lon")
    @Expose
    String longitude;

    public ResponseLocation(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }




}
