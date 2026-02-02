package com.campusFacilities.www.model.Hostel;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentVisitEntry {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long visitId;

	    // ================= STUDENT INFO (FROM TOKEN) =================
	    
	    @Column(name = "student_id", nullable = false)
	    private Long studentId;

	    @Column(name = "student_name", nullable = false)
	    private String studentName;

	    // ================= VISITOR INFO =================
	    @Column(name = "visitor_name", nullable = false)
	    private String visitorName;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = true)
	    private Relationship relationship;

	    @Column(name = "visitor_contact", nullable = false)
	    private String visitorContact;

	    // ================= VISIT DETAILS =================
	    @Column(name = "visit_date", nullable = false)
	    private LocalDate visitDate;

	    @Column(name = "visit_time", nullable = false)
	    private LocalTime visitTime;

	    @Column(name = "purpose_of_visit", length = 1000)
	    private String purposeOfVisit;

	    // ================= STATUS & AUDIT =================
	    @Enumerated(EnumType.STRING)
	    private VisitStatus status = VisitStatus.SCHEDULED;

	    @Column(name = "created_at", updatable = false)
	    private LocalDate createdAt = LocalDate.now();

	    // ================= ENUMS =================
	    public enum Relationship {
	        FATHER,
	        MOTHER,
	        GUARDIAN,
	        RELATIVE,
	        OTHER
	    }

	    public enum VisitStatus {
	        SCHEDULED,
	        CHECKED_IN,
	        CHECKED_OUT,
	        CANCELLED
	    }

}
