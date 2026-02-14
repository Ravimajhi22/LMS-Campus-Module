package com.campusFacilities.www.repository.marketing;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.marketing.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// Find by email
    Optional<Customer> findByEmail(String email);

    // Find by location
    List<Customer> findByLocation(String location);

    // Check if email exists
    boolean existsByEmail(String email);
}