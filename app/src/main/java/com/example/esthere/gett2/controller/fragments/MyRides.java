package com.example.esthere.gett2.controller.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.esthere.gett2.model.entities.Ride;
import com.example.esthere.gett2.utils.ContentProvider;
import com.example.esthere.gett2.utils.ManageLocation;

import java.util.ArrayList;


public class MyRides extends Fragment {
    protected ArrayList<Ride> rides;
    public MyRides() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rides, container, false);
        final ListView listMyRides = (ListView) view.findViewById(R.id.listMyRides);
        /*new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                return null;
            }
        }.execute();
*/
        Globals.backend.myRides(Globals.driver,new IAction<ArrayList<Ride>>() {
            @Override
            public void onSuccess(ArrayList<Ride> obj) {
                rides = obj;
                ArrayAdapter<Ride> adapter = new ArrayAdapter<Ride>( getActivity(), R.layout.ride, rides) {
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null)
                            convertView = View.inflate(getActivity(),R.layout.ride,null);

                        final EditText email = (EditText) convertView.findViewById(R.id.email);
                        final EditText name = (EditText) convertView.findViewById(R.id.name);
                        final EditText tel = (EditText) convertView.findViewById(R.id.tel);
                        EditText source = (EditText) convertView.findViewById(R.id.source);
                        EditText target = (EditText) convertView.findViewById(R.id.target);

                        Button claim = (Button) convertView.findViewById(R.id.claim);
                        Button unclaim = (Button) convertView.findViewById(R.id.unclaim);
                        final Button addToContant = (Button) convertView.findViewById(R.id.addContact);

                        unclaim.setTag(rides.get(position).getKey());
                        if(rides.get(position).getStatus().equals(Ride.Status.FINISHED)) {
                            unclaim.setVisibility(View.GONE);
                        }
                        unclaim.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String key = (String)((Button) view).getTag();
                                Globals.backend.UnClaim(key, new IAction<Boolean>() {
                                    @Override
                                    public void onSuccess(Boolean obj) {
                                        //Dialogs.Dialog(getActivity(),getString(R.string.FIREBASE),getString(R.string.CLAIMED),getString(R.string.BUTTON_CLOSE));
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
                        addToContant.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ContentProvider.addContact(getActivity(), name.getText().toString(), tel.getText().toString(), email.getText().toString());
                                addToContant.setEnabled(false);
                                //addToContant.setBackgroundColor(Color.rgb(211,211,211));
                            }
                        });
                        claim.setVisibility(View.GONE);

                        email.setText(rides.get(position).getEmail());
                        name.setText(rides.get(position).getName());
                        tel.setText(rides.get(position).getPhone());
                        source.setText(ManageLocation.addressFromLongLat(rides.get(position).getSourceLocation(), getActivity()));
                        target.setText(ManageLocation.addressFromLongLat(rides.get(position).getTargetLocation(), getActivity()));
                        return convertView;
                    }
                };

                listMyRides.setAdapter(adapter);
            }
            @Override
            public void onFailure(Exception exception) { }
        });
        return  view;
    }
}
