package com.campusFacilities.www.repository.Library;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.campusFacilities.www.model.Library.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {
	
	@Query("select b from Books b where b.isDeleted = false")
    List<Books> findByIsDeletedFalse();

    Optional<Books> findByIsbnAndIsDeletedFalse(String isbn);

    List<Books> findByCategory_IdAndIsDeletedFalse(Long categoryId);

    @org.springframework.data.jpa.repository.Modifying
    @Query("UPDATE Books b SET b.availableCopies = :copies, b.status = :status WHERE b.id = :id")
    void updateBookStatusAndCopies(Long id, Integer copies, Books.Status status);
}
