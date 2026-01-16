package com.campusFacilities.www.repository.Transport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Transport.TransportAttendance;

@Repository
public interface TransportAttendanceRepository
        extends JpaRepository<TransportAttendance, Long> {

    // Attendance of a student for a day
    Optional<TransportAttendance> findByStudentIdAndAttendanceDate(
            Long studentId, LocalDate attendanceDate);

    // All attendance for a bus on a date
    List<TransportAttendance> findByBusIdAndAttendanceDate(
            Long busId, LocalDate attendanceDate);

    // Attendance by route & date
    List<TransportAttendance> findByRouteIdAndAttendanceDate(
            Long routeId, LocalDate attendanceDate);

    // Pickup status report
    List<TransportAttendance> findByBusIdAndPickupStatus(
            Long busId,
            TransportAttendance.TransportAttendanceStatus pickupStatus);

    // Drop status report
    List<TransportAttendance> findByBusIdAndDropStatus(
            Long busId,
            TransportAttendance.TransportAttendanceStatus dropStatus);
}