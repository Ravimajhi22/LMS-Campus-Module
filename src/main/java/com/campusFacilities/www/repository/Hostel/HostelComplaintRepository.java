package com.campusFacilities.www.repository.Hostel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.HostelComplaint;

@Repository
public interface HostelComplaintRepository extends JpaRepository<HostelComplaint, Long> {}