package com.campusFacilities.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long>
{

    List<Stop> findByRouteRouteIdOrderBySequenceNumber(Long routeId);
}
