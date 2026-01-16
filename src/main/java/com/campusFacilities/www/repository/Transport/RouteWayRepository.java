package com.campusFacilities.www.repository.Transport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.RouteWay;

@Repository
public interface RouteWayRepository extends JpaRepository<RouteWay, Long> {

    // Get only active routes
    List<RouteWay> findByActiveTrue();

    // Get routes assigned to a vehicle
    List<RouteWay> findByAssigndVehicle_VechicleNumber(String vechicleNumber);
}