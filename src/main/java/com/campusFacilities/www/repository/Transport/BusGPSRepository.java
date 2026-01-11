package com.campusFacilities.www.repository.Transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.VehicleGPS;

@Repository
public interface BusGPSRepository extends JpaRepository<VehicleGPS, Long>
{

	static BusGPSRepository findTopByBusIdOrderByTimestampDesc(Long busId) {
		return null;
	}
	
}