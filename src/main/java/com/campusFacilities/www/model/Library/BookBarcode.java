package com.campusFacilities.www.model.Library;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book_barcodes")
@Data
public class BookBarcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barcodeId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @Column(nullable = false, unique = true)
    private String barcodeValue;

    private Boolean isIssued = false;
}