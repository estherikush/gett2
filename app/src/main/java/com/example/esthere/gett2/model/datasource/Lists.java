package com.example.esthere.gett2.model.datasource;

import com.example.esthere.gett2.model.backend.IAction;
import com.example.esthere.gett2.model.backend.IBackend;
import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.model.entities.Ride;

import java.util.ArrayList;

public class Lists implements IBackend{
    private ArrayList<Driver> drivers = new ArrayList<>();
    @Override
    public ArrayList<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    @Override
    public Boolean Login(Driver user) {
        return true;//(user.getEmail().equals("a") && user.getPassword().equals("1234"));
    }

    @Override
    public void addRide(Ride travel) {

    }

    @Override
    public void isExists(Ride travel, IAction<Boolean> action) {

    }

    @Override
    public void isExists(Driver driver, IAction<Boolean> action) {

    }

    @Override
    public void Authenticate(Driver driver, IAction<Boolean> action) {

    }

    @Override
    public void openRides(IAction<ArrayList<Ride>> action) {

    }

    @Override
    public void myRides(Driver driver, IAction<ArrayList<Ride>> action) {

    }

    @Override
    public void myFinishedRides(Driver driver, IAction<ArrayList<Ride>> action) {

    }

    @Override
    public void Claim(String key, String driverId, IAction<Boolean> action) {

    }

    @Override
    public void UnClaim(String key, IAction<Boolean> action) {

    }
}
