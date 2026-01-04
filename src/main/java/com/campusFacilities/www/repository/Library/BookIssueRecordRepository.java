package com.campusFacilities.www.repository.Library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.BookIssueRecord;

public interface BookIssueRecordRepository extends JpaRepository<BookIssueRecord, Long> {}