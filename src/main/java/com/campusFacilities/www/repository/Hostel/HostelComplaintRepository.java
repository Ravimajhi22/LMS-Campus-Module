package com.campusFacilities.www.repository.Hostel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.Hostel;
import com.campusFacilities.www.model.Hostel.HostelComplaint;
import com.campusFacilities.www.model.Hostel.HostelRoom;

@Repository
public interface HostelComplaintRepository extends JpaRepository<HostelComplaint, Long> {

    List<HostelComplaint> findByStudentId(Long studentId);

    
    List<HostelComplaint> findByStatus(HostelComplaint.ComplaintStatus status);
    
    Optional<Hostel> findByHostelName(String hostelName);
    
    Optional<HostelRoom> findByRoomNumber(String roomNumber);
}