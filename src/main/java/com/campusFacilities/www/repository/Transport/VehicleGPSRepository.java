package com.campusFacilities.www.repository.Transport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.VehicleGPS;

@Repository
public interface VehicleGPSRepository
        extends JpaRepository<VehicleGPS, Long> {

    // Latest GPS entry of a bus
    VehicleGPS findTopByBusIdOrderByTimestampDesc(Long busId);

    // Full GPS history of a bus
    List<VehicleGPS> findByBusIdOrderByTimestampDesc(Long busId);
}

