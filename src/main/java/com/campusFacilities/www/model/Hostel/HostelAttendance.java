package com.campusFacilities.www.model.Hostel;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class HostelAttendance {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    // ---------------- Student Info  ----------------
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    // ---------------- Room Info ----------------
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private HostelRoom room;

    // ---------------- Attendance ----------------
    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    public enum AttendanceStatus {
        PRESENT,
        ABSENT
    }

    // ---------------- Audit ----------------
    @Column(name = "marked_at")
    private LocalDate markedAt = LocalDate.now();


}