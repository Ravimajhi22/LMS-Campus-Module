package com.campusFacilities.www.repository.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
	
	List<BookCategory> findByIsDeletedFalse();
}