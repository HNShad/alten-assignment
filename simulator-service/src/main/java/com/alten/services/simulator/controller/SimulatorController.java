package com.alten.services.simulator.controller;

import com.alten.services.simulator.model.VehicleData;
import com.alten.services.simulator.service.VehicleSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class SimulatorController {

    private static final String DISCONNECTED = "Disconnected";

    @Autowired
    private VehicleSimulator vehicleSimulator;

    /**
     * This method is to simulate ping to every vehicle.
     *
     * @param vin
     * @param regnr
     * @return
     */
    @GetMapping("/ping")
    public String pingVehicleForStatus(@RequestParam("vin") String vin,
                                       @RequestParam("regnr") String regnr) {

        // Fill up the data for first request and then randomize pingable vehicles
        if (VehicleData.getVehicles().size() > 0) {
            vehicleSimulator.randomizeUnreachableVehicles();
        }
        else {
            vehicleSimulator.fetchAllVehicles();
        }

        return VehicleData.getVehicles()
                .stream()
                .filter(v -> v.getVin().equals(vin) && v.getRegnr().equals(regnr))
                .findFirst()
                .map(vehicle -> vehicle.getPingable() ? vehicle.getStatus() : DISCONNECTED)
                .orElse(DISCONNECTED);
    }
}