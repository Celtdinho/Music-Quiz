package com.yourapp.musicquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class GPSTracker {

    private final Context context;

    public GPSTracker(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public double getLatitude() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return loc != null ? loc.getLatitude() : 0;
    }

    @SuppressLint("MissingPermission")
    public double getLongitude() {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        return loc != null ? loc.getLongitude() : 0;
    }
}
