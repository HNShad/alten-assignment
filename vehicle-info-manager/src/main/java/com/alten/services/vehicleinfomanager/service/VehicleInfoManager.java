package com.alten.services.vehicleinfomanager.service;

import com.alten.services.vehicleinfomanager.model.Vehicle;
import com.alten.services.vehicleinfomanager.model.VehiclesWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.function.Consumer;

@Component
public class VehicleInfoManager {

    private static final String DISCONNECTED = "Disconnected";

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedDelay = 5000)
    public void pingVehicles() {


        VehiclesWrapper vehiclesList = restTemplate.getForObject("http://data-service/vehicles/find", VehiclesWrapper.class);


        Consumer<Vehicle> pingVehicle = new Consumer<Vehicle>() {
            @Override
            public void accept(Vehicle vehicle) {
                String vehicleStatus =
                        restTemplate.getForObject("http://simulator-service/vehicles/ping?vin=" + vehicle.getVin() + "&regnr=" + vehicle.getRegnr(), String.class);

                if (!StringUtils.isNotEmpty(vehicleStatus)) {

                    vehicleStatus = DISCONNECTED;
                }

                //If the vehicle status has changed
                if (!vehicle.getStatus().equals(vehicleStatus)) {

                    String statusUpdated =
                            restTemplate.getForObject("http://data-service/vehicles/update?vin=" + vehicle.getVin() + "&regnr=" + vehicle.getRegnr() + "&status=" + vehicleStatus, String.class);
                }
            }
        };

        vehiclesList.getVehicleList().forEach(pingVehicle);
    }
}
