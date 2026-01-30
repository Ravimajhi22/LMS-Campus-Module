package com.campusFacilities.www.repository.Hostel;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Hostel.HostelAttendance;

@Repository
public interface HostelAttendanceRepository extends JpaRepository<HostelAttendance, Long> {

    List<HostelAttendance> findByAttendanceDate(LocalDate date);

    List<HostelAttendance> findByStudentId(Long studentId);
}