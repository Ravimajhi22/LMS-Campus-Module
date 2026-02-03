package com.campusFacilities.www.repository.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.BookReservation;

public interface BookReservationRepository extends JpaRepository<BookReservation, Long> {
	
	List<BookReservation> findByIsDeletedFalse();

	// Find all active reservations for a user
	List<BookReservation> findByUserIdAndIsDeletedFalse(Long userId);

	// Find all active reservations for a book
	List<BookReservation> findByBookIdAndIsDeletedFalse(Long bookId);

	// Find by Status
	List<BookReservation> findByStatusAndIsDeletedFalse(BookReservation.Status status);

	// Find expired user reservations: reserveUntil < now AND status = RESERVED
	List<BookReservation> findByReserveUntilBeforeAndStatusAndIsDeletedFalse(java.time.LocalDate date,
			BookReservation.Status status);

	// Find expired admin holds: adminHoldUntil < now AND status = AVAILABLE
	// Find expired admin holds: adminHoldUntil < now AND status = AVAILABLE
	List<BookReservation> findByAdminHoldUntilBeforeAndStatusAndIsDeletedFalse(java.time.LocalDate date,
			BookReservation.Status status);

	List<BookReservation> findByBookIdAndUserIdAndIsDeletedFalse(Long bookId, Long userId);

}