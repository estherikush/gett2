package com.example.esthere.gett2.model.datasource;

import java.util.ArrayList;

import com.example.esthere.gett2.model.backend.IAction;
import com.example.esthere.gett2.model.backend.IBackend;
import com.example.esthere.gett2.model.entities.Customer;
import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.model.entities.Ride;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.esthere.gett2.model.*;

public class FireBase implements IBackend {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rides = db.getReference("Ride");
    DatabaseReference drivers = db.getReference("Driver");

    @Override
    public void addRide(Ride ride) {
        //rides.child(ride.getKey()).setValue(ride);
        return;
    }

    @Override
    public ArrayList<Driver> getDrivers() {
        return null;
    }

    @Override
    public void addDriver(Driver driver) {
        drivers.child(driver.getEmail()).setValue(driver);
        return;
    }

    @Override
    public Boolean Login(Driver user) {
        return null;
    }


    @Override
    public void isExists(final Ride travel, final IAction<Boolean> action) {
        rides.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //action.onSuccess(snapshot.hasChild(travel.getKey()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                action.onFailure(databaseError.toException());
            }
        });
    }

    @Override
    public void isExists(final Driver driver, final IAction<Boolean> action) {
        drivers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //action.onSuccess(snapshot.hasChild(driver.getKey()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                action.onFailure(databaseError.toException());
            }
        });
    }

    @Override
    public void Authenticate(final Driver driver, final IAction<Boolean> action) {
//        drivers.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                //String key = driver.getKey();
//                if (snapshot.hasChild(key)) {
//                    Driver d = snapshot.child(key).getValue(Driver.class);
//                    if (d.getPassword().equals(driver.getPassword())){
//                        Globals.driver = d;
//                        action.onSuccess(true);
//                    } else
//                        action.onSuccess(false);
//                } else {
//                    action.onSuccess(false);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                action.onFailure(databaseError.toException());
//            }
//        });
    }

    @Override
    public void myFinishedRides(Driver driver, IAction<ArrayList<Ride>> action) {

    }

    @Override
    public void openRides(final IAction<ArrayList<Ride>> action) {
        rides.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<Ride> results = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren()) {
                    Ride travel = item.getValue(Ride.class);
                    if (travel.getStatus().equals(Ride.Status.AVAILABLE))
                        results.add(travel);
                }
                action.onSuccess(results);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                action.onFailure(databaseError.toException());
            }
        });
    }

    @Override
    public void myRides(final Driver driver, final IAction<ArrayList<Ride>> action) {
        rides.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<Ride> results = new ArrayList<>();
//                for (DataSnapshot item: snapshot.getChildren()) {
//                    Ride travel = item.getValue(Ride.class);
//                    if (travel.getDriverId() != null )
//                        if (travel.getDriverId().equals(driver.getEmail()) && travel.getStatus().equals(Travel.Status.InProgress) )
//                            results.add(travel);
//                }

                action.onSuccess(results);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                action.onFailure(databaseError.toException());
            }
        });
    }

    @Override
    public void Claim(final String key,final String email, final IAction<Boolean> action) {
        rides.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.hasChild(key)) {
//                    Ride ride = snapshot.child(key).getValue(Ride.class);
//                    ride.setDriverId(email);
//                    ride.setStatus(Ride.Status.InProgress);
//                    ride.child(key).setValue(ride);
//                    action.onSuccess(true);
//                } else {
//                    action.onSuccess(false);
//                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                action.onFailure(databaseError.toException());
            }
        });
    }

    @Override
    public void UnClaim(final String key, final IAction<Boolean> action) {
        rides.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.hasChild(key)) {
//                    Ride travel = snapshot.child(key).getValue(Travel.class);
//                    travel.setStatus(Travel.Status.Finished);
//                    rides.child(key).setValue(travel);
//                    action.onSuccess(true);
//                } else {
//                    action.onSuccess(false);
//                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                action.onFailure(databaseError.toException());
            }
        });
    }
}
