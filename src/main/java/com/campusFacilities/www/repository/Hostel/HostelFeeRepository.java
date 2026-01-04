package com.campusFacilities.www.repository.Hostel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.HostelFee;

@Repository
public interface HostelFeeRepository extends JpaRepository<HostelFee, Long> {}
