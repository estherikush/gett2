package com.example.esthere.gett2.model.backend;

import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.model.entities.Ride;

import java.util.ArrayList;

public interface IBackend {
    public ArrayList<Driver> getDrivers();
    public void addDriver(Driver driver);
    //public ArrayList<Ride> getUn
    public Boolean Login(Driver user);

    void addRide(Ride travel);

    void isExists(Ride travel, IAction<Boolean> action);
    void isExists(Driver driver, IAction<Boolean> action);
    void Authenticate(Driver driver, IAction<Boolean> action);
    void openRides(IAction<ArrayList<Ride>> action);
    void myRides(Driver driver,IAction<ArrayList<Ride>> action);
    void myFinishedRides(Driver driver,IAction<ArrayList<Ride>> action);
    void Claim(String key,String driverId,IAction<Boolean> action);
    void UnClaim(String key,IAction<Boolean> action);
    //void addContact(String key, IAction<Boolean> action);
}
