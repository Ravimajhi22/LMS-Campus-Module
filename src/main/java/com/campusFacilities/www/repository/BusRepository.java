package com.campusFacilities.www.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campusFacilities.www.model.Transport.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    // Update bus number
    @Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.busNumber = :busNumber WHERE b.busId = :busId")
    int updateBusNumber(Long busId, String busNumber);

    // Update driver name
    @Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.driverName = :driverName WHERE b.busId = :busId")
    int updateDriverName(Long busId, String driverName);

    // Update driver contact
    @Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.driverContact = :driverContact WHERE b.busId = :busId")
    int updateDriverContact(Long busId, String driverContact);

    // Update capacity
    @Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.capacity = :capacity WHERE b.busId = :busId")
    int updateCapacity(Long busId, int capacity);

    // You can add more fields as needed
}

