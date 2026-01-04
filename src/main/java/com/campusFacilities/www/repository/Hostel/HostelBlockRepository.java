package com.campusFacilities.www.repository.Hostel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.HostelBlock;

@Repository
public interface HostelBlockRepository extends JpaRepository<HostelBlock, Long> {}

