package com.example.esthere.gett2.model.entities;

import android.location.Location;
import android.os.AsyncTask;

import java.sql.Date;
import java.sql.Time;

public class Ride {
    public enum  Status {
        AVAILABLE,
        BUSY,
        FINISHED
    }
    private myLocation targetLocation;
    private myLocation sourceLocation;
    //private Integer status;
    private Status status;
    private Date timeRide;
    private boolean arrivingOrLeaving;
    private Date realLeavingTime;
    private Date realArrivingTime;
    private String name;
    private String phone;
    private String email;
    private String driverId;
    private String key;

    //constructors
    public Ride(){}
    public Ride(myLocation targetLocation, myLocation sourceLocation, Status status) {
        this.targetLocation = targetLocation;
        this.sourceLocation = sourceLocation;
        this.status = status;
    }

    public Ride(myLocation targetLocation, myLocation sourceLocation, Status status, Date timeRide, boolean arrivingOrLeaving, Date realLeavingTime, Date realArrivingTime, String name, String phone, String email) {
        this.targetLocation = targetLocation;
        this.sourceLocation = sourceLocation;
        this.status = status;
        this.timeRide = timeRide;
        this.arrivingOrLeaving = arrivingOrLeaving;
        this.realLeavingTime = realLeavingTime;
        this.realArrivingTime = realArrivingTime;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    //getters &setters

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTimeRide() {
        return timeRide;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public void setTimeRide(Date timeRide) {
        this.timeRide = timeRide;
    }

    public Date getRealLeavingTime() {
        return realLeavingTime;
    }

    public void setRealLeavingTime(Date realLeavingTime) {
        this.realLeavingTime = realLeavingTime;
    }

    public Date getRealArrivingTime() {
        return realArrivingTime;
    }

    public void setRealArrivingTime(Date realArrivingTime) {
        this.realArrivingTime = realArrivingTime;
    }

    public Location getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(myLocation targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Location getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(myLocation sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public Status getStatus() {
        return status;
    }


    public boolean isArrivingOrLeaving() {
        return arrivingOrLeaving;
    }

    public void setArrivingOrLeaving(boolean arrivingOrLeaving) {
        this.arrivingOrLeaving = arrivingOrLeaving;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer getCustomer()
    {
        return new Customer(name,email,phone);
    }
}
