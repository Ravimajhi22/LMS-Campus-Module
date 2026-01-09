package com.campusFacilities.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.BusGPS;

@Repository
public interface BusGPSRepository extends JpaRepository<BusGPS, Long>
{

	static BusGPSRepository findTopByBusIdOrderByTimestampDesc(Long busId) {
		return null;
	}
	
}