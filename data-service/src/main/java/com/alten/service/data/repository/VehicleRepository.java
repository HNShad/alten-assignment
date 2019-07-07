package com.alten.service.data.repository;

import com.alten.service.data.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    List<Vehicle> findByVin(String vin);
    List<Vehicle> findByRegnr(String regnr);
    List<Vehicle> findByCustomer(String customer);
    List<Vehicle> findByStatus(String status);
    Vehicle findByVinAndRegnr(String vin, String regnr);

    @Modifying
    @Transactional
    @Query(value = "update vehicle v set v.status = ?3 where v.vin = ?1 and v.regnr = ?2 ", nativeQuery = true)
    int updateVehicle(String vin, String regnr, String status);

    @Query(value = "select distinct customer from vehicle", nativeQuery = true)
    List<String> findDistinctCustomer();

}
