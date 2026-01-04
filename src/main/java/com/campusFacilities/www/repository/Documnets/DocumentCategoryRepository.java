package com.campusFacilities.www.repository.Documnets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Documents.DocumentCategory;

@Repository
public interface DocumentCategoryRepository extends JpaRepository<DocumentCategory, Long> {}
