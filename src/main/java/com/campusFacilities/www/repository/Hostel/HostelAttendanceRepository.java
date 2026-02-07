package com.campusFacilities.www.repository.Hostel;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.Hostel.HostelAttendance;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation;
import com.campusFacilities.www.model.Hostel.StudentHostelAllocation.AllocationStatus;

@Repository
public interface HostelAttendanceRepository extends JpaRepository<HostelAttendance, Long> 
{
    List<HostelAttendance> findByAttendanceDate(LocalDate attendanceDate);
    
    Optional<StudentHostelAllocation> findByStudentId(Long studentId);
    
    Optional<HostelAttendance>
    findTopByStudentIdOrderByAttendanceIdDesc(Long studentId);
    
    boolean existsByStudentIdAndAttendanceDate(Long studentId, LocalDate date);
   
    Optional<StudentHostelAllocation>
    findByStudentIdAndStatus(Long studentId, AllocationStatus status);
 
}