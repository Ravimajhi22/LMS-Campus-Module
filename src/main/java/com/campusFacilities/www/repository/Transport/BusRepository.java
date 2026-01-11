package com.campusFacilities.www.repository.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campusFacilities.www.model.Transport.Vehicle;

@Repository
public interface BusRepository extends JpaRepository<Vehicle, Long> {

    // Update bus number
    @Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.busNumber = :busNumber WHERE b.busId = :busId")
    int updateBusNumber(Long busId, String busNumber);

    // Update capacity
    @Modifying
    @Transactional
    @Query("UPDATE Bus b SET b.capacity = :capacity WHERE b.busId = :busId")
    int updateCapacity(Long busId, int capacity);

    // You can add more fields as needed
}

