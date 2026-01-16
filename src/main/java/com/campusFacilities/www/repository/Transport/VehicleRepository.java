package com.campusFacilities.www.repository.Transport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.Vehicle;
import com.campusFacilities.www.model.Transport.VehicleGPS;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

   
    List<Vehicle> findByVehicleStatus(Vehicle.VehicleStatus vehicleStatus);

 
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
    
    List<VehicleGPS> findByContactNumber(String contactNumber);

}
