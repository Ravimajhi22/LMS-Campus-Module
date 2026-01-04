package com.campusFacilities.www.repository.Library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusFacilities.www.model.Library.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {}