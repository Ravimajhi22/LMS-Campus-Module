package com.campusFacilities.www.repository.Hostel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {}
