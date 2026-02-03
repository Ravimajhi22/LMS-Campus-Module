package com.campusFacilities.www.repository.Library;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusFacilities.www.model.Library.BookBarcode;

@Repository
public interface BookBarcodeRepository extends JpaRepository<BookBarcode, Long> {
    Optional<BookBarcode> findByBarcodeValue(String barcodeValue);
}
