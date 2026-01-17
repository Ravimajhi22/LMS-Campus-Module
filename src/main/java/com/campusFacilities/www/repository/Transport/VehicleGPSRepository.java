package com.campusFacilities.www.repository.Transport;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.VehicleGPS;

@Repository
public interface VehicleGPSRepository
        extends JpaRepository<VehicleGPS, Long> 
{ 
    List<VehicleGPS> findByVehicle_Id(Long vehicleId);

   
    List<VehicleGPS> findByVehicle_VehicleNumber(String vehicleNumber);

  
    Optional<VehicleGPS> findTopByVehicle_IdOrderByTimestampDesc(Long vehicleId);
    
}

