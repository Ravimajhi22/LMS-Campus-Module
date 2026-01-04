package com.campusFacilities.www.repository.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.LibrarySettings;

public interface LibrarySettingsRepository extends JpaRepository<LibrarySettings, Long> {
	
	List<LibrarySettings> findByIsDeletedFalse();
	
}