package com.campusFacilities.www.repository.Library;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.BookReservation;
import com.campusFacilities.www.model.Library.LibrarySettings;

public interface LibrarySettingsRepository extends JpaRepository<LibrarySettings, Long> {
	
	List<LibrarySettings> findByIsDeletedFalse();

	Optional<LibrarySettings> findFirstByIsDeletedFalse();

	Optional<LibrarySettings> findByMemberRoleAndIsDeletedFalse(String memberRole);

	BookReservation save(BookReservation reservation);
	
}