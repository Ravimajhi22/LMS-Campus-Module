package com.campusFacilities.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Transport.BusRoute; 
public interface BusRouteMappingRepository extends JpaRepository<BusRoute, Long> 
{
	 List<BusRoute> findByBus_BusId(Long busId);

	    List<BusRoute> findByRoute_RouteId(Long routeId);
}

