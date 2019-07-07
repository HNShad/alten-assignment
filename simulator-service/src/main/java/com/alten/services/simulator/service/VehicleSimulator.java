package com.alten.services.simulator.service;

import com.alten.services.simulator.model.VehicleData;
import com.alten.services.simulator.model.VehiclesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleSimulator {

    private static final String CONNECTED = "Connected";
    private static final String DISCONNECTED = "Disconnected";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Pumping List of Vehicles from Database service and then simulate every vehicle from this list.
     *
     */
    public void fetchAllVehicles() {

        VehiclesWrapper vehiclesList = restTemplate.getForObject("http://data-service/vehicles/find", VehiclesWrapper.class);

        VehicleData.setVehicles(vehiclesList.getVehicleList());

        randomizeUnreachableVehicles();
    }


    /**
     * This method tries to make some Vehicles unreachable
     */
    public void randomizeUnreachableVehicles() {

        int size = VehicleData.getVehicles().size();

        int randomValue = (int )(Math.random() * size + 0);

        for (int i = 0; i < size; i++) {

            if (i == randomValue) {
                VehicleData.getVehicles().get(i).setPingable(false);
            }
            else {
                VehicleData.getVehicles().get(i).setPingable(true);
            }
        }
    }

    /**
     * This methods scheduled so every Vehicle sends its status one time per minute (60 seconds)
     */
    @Scheduled(fixedDelay = 60000)
    public void sendVehicleStatus() {

        VehicleData.getVehicles()
                .stream()
                .forEach(vehicle -> {
                    if (!vehicle.getPingable()) {
                        vehicle.setStatus(DISCONNECTED);
                    }
                    else {
                        vehicle.setStatus(CONNECTED);
                    }

                    String response =
                            restTemplate.getForObject("http://data-service/vehicles/update?vin=" + vehicle.getVin() + "&regnr=" + vehicle.getRegnr() + "&status=" + vehicle.getStatus(), String.class);
                });
    }
}
