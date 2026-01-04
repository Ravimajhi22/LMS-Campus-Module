package com.campusFacilities.www.repository.Hostel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;

@Repository
public interface StudentHostelAllocationRepository extends JpaRepository<StudentHostelAllocation, Long> {}