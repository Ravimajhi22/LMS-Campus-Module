package com.campusFacilities.www.repository.Hostel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.HostelComplaint;

@Repository
public interface HostelComplaintRepository extends JpaRepository<HostelComplaint, Long> {

    List<HostelComplaint> findByStudentId(Long studentId);

    
    List<HostelComplaint> findByStatus(HostelComplaint.ComplaintStatus status);
}