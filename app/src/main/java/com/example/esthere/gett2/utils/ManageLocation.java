package com.example.esthere.gett2.utils;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ManageLocation {
    public static String addressFromLongLat(Location l, Activity activity) {

        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        String address2 = "";
        try {
            List<Address> allAddresses = geocoder.getFromLocation(l.getLatitude(), l.getLongitude(), 1);
            if (allAddresses.size() > 0) {
                Address address = allAddresses.get(0);
                address2 = address.getAddressLine(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address2;

    }
}
