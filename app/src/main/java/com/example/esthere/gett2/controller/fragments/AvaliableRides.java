package com.example.esthere.gett2.controller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.esthere.gett2.R;
import com.example.esthere.gett2.model.backend.IAction;
import com.example.esthere.gett2.model.datasource.Globals;
import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.model.entities.Ride;
import com.example.esthere.gett2.utils.ManageLocation;

import java.util.ArrayList;


public class AvaliableRides extends Fragment {

    protected ArrayList<Ride> rides;

    public AvaliableRides() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_avaliable_rides, container, false);
        final ListView listOpenTravels = (ListView) view.findViewById(R.id.listOpenTravels);

        try {
            Globals.backend.openRides(new IAction<ArrayList<Ride>>() {
                @Override
                public void onSuccess(ArrayList<Ride> obj) {
                    rides = obj;
                    try {
                        if (rides != null && getActivity() != null) {
                            ArrayAdapter<Ride> adapter = new ArrayAdapter<Ride>(getActivity(), R.layout.ride, rides) {
                                @NonNull
                                @Override
                                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                    if (convertView == null)
                                        convertView = View.inflate(getActivity(), R.layout.ride, null);

                                    EditText name = (EditText) convertView.findViewById(R.id.name);
                                    EditText tel = (EditText) convertView.findViewById(R.id.tel);
                                    EditText email = (EditText) convertView.findViewById(R.id.email);
                                    EditText source = (EditText) convertView.findViewById(R.id.source);
                                    EditText target = (EditText) convertView.findViewById(R.id.target);
                                    Button claim = (Button) convertView.findViewById(R.id.claim);
                                    Button unclaim = (Button) convertView.findViewById(R.id.unclaim);
                                    //!!!!!!!!!!!!!!!!!!!Button addToContant = (Button) convertView.findViewById(R.id.addToContant);
                                    EditText start = (EditText) convertView.findViewById(R.id.start);
                                    if (rides.size() > 0) {
                                        claim.setTag(rides.get(position).getKey());
                                        //Globals.customer = rides.get(position).getCustomer();
                                        claim.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                String key = (String) ((Button) view).getTag();
                                                Globals.backend.Claim(key, Globals.driver.getEmail(), new IAction<Boolean>() {
                                                    @Override
                                                    public void onSuccess(Boolean obj) {
                                                        // Dialogs.Dialog(getActivity(),getString(R.string.FIREBASE),getString(R.string.CLAIMED),getString(R.string.BUTTON_CLOSE));
                                                        return;
                                                    }

                                                    @Override
                                                    public void onFailure(Exception ex) {
                                                        // Dialogs.Dialog(getActivity(),getString(R.string.FIREBASE),ex.getMessage(),getString(R.string.BUTTON_CLOSE));
                                                        return;
                                                    }
                                                });

                                            }
                                        });

                                        unclaim.setVisibility(View.GONE);
                                        //addToContant.setVisibility(View.GONE);

                                        name.setText(rides.get(position).getCustomer().getName());
                                        tel.setText(rides.get(position).getCustomer().getTel());
                                        email.setText(rides.get(position).getCustomer().getEmail());
                                        source.setText(ManageLocation.addressFromLongLat(rides.get(position).getSourceLocation(), getActivity()));
                                        target.setText(ManageLocation.addressFromLongLat(rides.get(position).getTargetLocation(), getActivity()));
                                        //start.setText(rides.get(position).getRealLeavingTime().toString());
                                    }
                                    return convertView;
                                }
                            };
                            listOpenTravels.setAdapter(adapter);
                        }

                    } catch (Exception e) {
                        Log.w("GetTaxi2", "Error In Open Rides " + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Exception exception) {
                }
            });

        } catch (Exception e) {

        }


        return view;
    }


}
