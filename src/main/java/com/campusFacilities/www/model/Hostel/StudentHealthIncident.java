package com.campusFacilities.www.model.Hostel;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student_health_incidents")
@Data
public class StudentHealthIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incidentId;

    // ---------------- Student Info (from token / allocation) ----------------
    @Column(nullable = false)
    private Long studentId;

    private String studentName;

    private String studentPhone;

    private String parentPhone;

    // ---------------- Incident Details ----------------
    @Column(nullable = false)
    private String complaintNature;

    @Enumerated(EnumType.STRING)
    private Severity severity;
    public enum Severity {
        LOW,
        MEDIUM,
        HIGH,
        CRITICAL
    }

    @Enumerated(EnumType.STRING)
    private IncidentStatus currentStatus;
    public enum IncidentStatus {
        OBSERVATION,
        MEDICATED,
        HOSPITALIZED,
        RECOVERED
    }

    @Column(nullable = false)
    private LocalDate reportedDate;

    // ---------------- Clinical Notes ----------------
    @Column(length = 1000)
    private String clinicalNotes; 

    // ---------------- Audit ----------------
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}