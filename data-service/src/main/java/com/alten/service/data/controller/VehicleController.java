package com.alten.service.data.controller;

import com.alten.service.data.model.CustomersWrapper;
import com.alten.service.data.model.VehiclesWrapper;
import com.alten.service.data.repository.VehicleRepository;
import com.alten.service.data.validation.VehicleStatusConstraint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/find")
    public VehiclesWrapper findVehicle(@RequestParam(name="vin", required = false) String vin,
                                      @RequestParam(name="regnr", required = false) String regnr,
                                      @RequestParam(name="customer", required = false) String customer,
                                       @RequestParam(name="status", required = false) String status) {

        VehiclesWrapper vehiclesWrapper = new VehiclesWrapper();

        if (StringUtils.isNotEmpty(vin)) {
            vehiclesWrapper.setVehicleList(vehicleRepository.findByVin(vin));
        }
        else if (StringUtils.isNotEmpty(regnr)) {
            vehiclesWrapper.setVehicleList(vehicleRepository.findByRegnr(regnr));
        }
        else if (StringUtils.isNotEmpty(customer)) {
            vehiclesWrapper.setVehicleList(vehicleRepository.findByCustomer(customer));
        }
        else if (StringUtils.isNotEmpty(status)) {
            vehiclesWrapper.setVehicleList(vehicleRepository.findByStatus(status));
        }
        else {
            vehiclesWrapper.setVehicleList(vehicleRepository.findAll());
        }

        return vehiclesWrapper;
    }

    @GetMapping("/customers")
    public CustomersWrapper findCustomers(@RequestParam(name="customer", required = false) String customer) {

        CustomersWrapper customersWrapper = new CustomersWrapper();
        customersWrapper.setCustomers(vehicleRepository.findDistinctCustomer());

        return customersWrapper;
    }

    @GetMapping("/update")
    public String udpateVehicleStatus(@RequestParam(value = "vin", required = true) String vin,
                                                  @RequestParam(value = "regnr", required = true) String regnr,
                                                  @RequestParam(name="status", required = true) @VehicleStatusConstraint  String status) {

        int updatedVehicles = vehicleRepository.updateVehicle(vin, regnr, status);

        if (updatedVehicles == 1) {
            return "OK";
        }
        else {
            return "UPDATE-FAILED";
        }
    }

}
