package com.campusFacilities.www.repository.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.LibraryFine;

public interface LibraryFineRepository extends JpaRepository<LibraryFine, Long> {
	
	List<LibraryFine> findByIsDeletedFalse();	
}