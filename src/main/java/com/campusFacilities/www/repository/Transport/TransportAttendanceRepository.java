package com.campusFacilities.www.repository.Transport;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.campusFacilities.www.model.Transport.TransportAttendance;
import com.campusFacilities.www.model.Transport.Vehicle;

@Repository
public interface TransportAttendanceRepository
        extends JpaRepository<TransportAttendance, Long> {

	// Attendance by vehicle + date
    List<TransportAttendance>
    findByVehicle_IdAndAttendanceDate(Long vehicleId, LocalDate attendanceDate);

    // Attendance by vehicle ID
    List<TransportAttendance>
    findByVehicle_Id(Long vehicleId);

    // Attendance by vehicle number (MATCH ENTITY FIELD)
    List<TransportAttendance>
    findByVehicle_VehicleNumber(String vehicleNumber);

    // Attendance by student
    List<TransportAttendance>
    findByStudentId(Long studentId);

    // Attendance by date
    List<TransportAttendance>
    findByAttendanceDate(LocalDate attendanceDate);
    
    boolean existsByStudentIdAndAttendanceDate(
            Long studentId,
            LocalDate attendanceDate
    );

    boolean existsByStudentIdAndAttendanceDateAndMarkedBy(
            Long studentId,
            LocalDate attendanceDate,
            TransportAttendance.MarkedBy markedBy
    );

    Optional<TransportAttendance> findByStudentIdAndVehicleAndAttendanceDate(
    	    Long studentId, Vehicle vehicle, LocalDate date
    	);

}