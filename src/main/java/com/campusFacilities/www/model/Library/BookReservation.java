package com.campusFacilities.www.model.Library;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book_reservations")
@Data
public class BookReservation {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "reservation_id")
	    private Long id;

	    // Book being reserved
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "book_id", nullable = false)
	    private Books book;

	    // User who reserved (kept as ID intentionally)
	    @Column(name = "user_id", nullable = false)
	    private Long userId;

	    // ===== USER RESERVATION WINDOW =====

	    @Column(name = "reserved_at", nullable = false)
	    private LocalDate reservedAt;

	    @Column(name = "reservation_date", nullable = true)
	    private LocalDate reservationDate;

	    @Column(name = "reserve_until", nullable = true)
	    private LocalDate reserveUntil;

	    // ===== ADMIN HOLD WINDOW =====

	    @Column(name = "admin_hold_from", nullable = true)
	    private LocalDate adminHoldFrom;

	    @Column(name = "admin_hold_until", nullable = true)
	    private LocalDate adminHoldUntil;

	    // ===== STATUS =====

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Status status;

	    // ===== SOFT DELETE =====

	    @Column(name = "is_deleted", nullable = false)
	    private Boolean isDeleted = false;

	    // ===== AUDIT =====

	    @Column(name = "created_at", nullable = false, updatable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at", nullable = false)
	    private LocalDateTime updatedAt;

	    // ===== LIFECYCLE =====

	    @PrePersist
	    protected void onCreate() {
	        LocalDateTime now = LocalDateTime.now();
	        createdAt = now;
	        updatedAt = now;

	        if (reservedAt == null) {
	            reservedAt = LocalDate.now();
	        }

	        if (status == null) {
	            status = Status.RESERVED;
	        }

	        if (isDeleted == null) {
	            isDeleted = false;
	        }
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        updatedAt = LocalDateTime.now();
	    }

	    // ===== STATUS FLOW =====
	    public enum Status {
	        RESERVED, // user hold window active
	        AVAILABLE, // admin hold window active
	        COLLECTED, // book collected
	        CANCELLED, // cancelled manually
	        NO_RESPONSE // user never came
	    }
	}