package com.alten.services.simulator.model;

import java.util.List;

public class VehiclesWrapper {

    private List<Vehicle> vehicleList;

    public VehiclesWrapper() {
    }

    public VehiclesWrapper(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
