package com.alten.services.simulator.model;


public class Vehicle {

    private String vin;
    private String regnr;
    private String status;
    private Boolean pingable;

    public Vehicle() {
    }

    public Vehicle(String vin, String regnr, String status, Boolean pingable) {
        this.vin = vin;
        this.regnr = regnr;
        this.status = status;
        this.pingable = pingable;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegnr() {
        return regnr;
    }

    public void setRegnr(String regnr) {
        this.regnr = regnr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPingable() {
        return pingable;
    }

    public void setPingable(Boolean pingable) {
        this.pingable = pingable;
    }
}