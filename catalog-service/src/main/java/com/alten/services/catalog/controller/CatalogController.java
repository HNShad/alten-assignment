package com.alten.services.catalog.controller;

import com.alten.services.catalog.model.CustomersWrapper;
import com.alten.services.catalog.model.VehiclesWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private static final String DATA_SERVICE_FIND_URI = "http://data-service/vehicles/find";
    private static final String DATA_SERVICE_CUSTOMERS_URI = "http://data-service/vehicles/customers";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/find")
    public String findVehicles(@RequestParam(name="customer", required = false) String customer,
                               @RequestParam(name="status", required = false) String status,
                               @RequestParam(name="vehicle", required = false) String allVehicles,
                               Model model) {

        VehiclesWrapper vehiclesList = null;

        if (StringUtils.isNotEmpty(status)) {

            vehiclesList = restTemplate.getForObject(DATA_SERVICE_FIND_URI + "?status=" + status, VehiclesWrapper.class);
        }
        else if (StringUtils.isNotEmpty(customer)) {

            vehiclesList = restTemplate.getForObject(DATA_SERVICE_FIND_URI + "?customer=" + customer, VehiclesWrapper.class);
        }
        else if (StringUtils.isNotEmpty(allVehicles) && "all".equals(allVehicles)) {

            vehiclesList = restTemplate.getForObject(DATA_SERVICE_FIND_URI, VehiclesWrapper.class);
        }
        else {
            return "errorpage";
        }

        model.addAttribute("vehicles", vehiclesList.getVehicleList());


        CustomersWrapper customersWrapper = restTemplate.getForObject(DATA_SERVICE_CUSTOMERS_URI, CustomersWrapper.class);
        model.addAttribute("customers", customersWrapper.getCustomers());

        return "vehicle-catalog";
    }
}