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
    private Location targetLocation;
    private Location sourceLocation;
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
    //constructors
    public Ride(){}
    public Ride(Location targetLocation, Location sourceLocation, Status status) {
        this.targetLocation = targetLocation;
        this.sourceLocation = sourceLocation;
        this.status = status;
    }

    public Ride(Location targetLocation, Location sourceLocation, Status status, Date timeRide, boolean arrivingOrLeaving, Date realLeavingTime, Date realArrivingTime, String name, String phone, String email) {
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

    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    public Location getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(Location sourceLocation) {
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

}
