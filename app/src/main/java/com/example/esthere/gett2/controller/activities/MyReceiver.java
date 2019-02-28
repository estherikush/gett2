package com.example.esthere.gett2.controller.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case "com.example.esthere.gett1":
                String userName = intent.getExtras().getString("user");
                String password = intent.getExtras().getString("password");
                Float lng = intent.getExtras().getFloat("lng");
                Float lat = intent.getExtras().getFloat("lat");
                Toast.makeText(context, "Received Event: Ride was added" + userName + "," + password + "," + lng + "," + lat, Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
