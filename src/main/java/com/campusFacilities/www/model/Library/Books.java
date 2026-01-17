package com.campusFacilities.www.model.Library;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    // ================= BASIC INFORMATION =================//
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String publisher;

    @ElementCollection
    @CollectionTable(name = "book_categories", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "category")
    private List<String> categories;
    
    private String edition;   

    @Column(length = 4)
    private String Year; 
    
    private String Language;
    
    // ============= INVENTORY DETAILS =============//

    @Column(nullable = false, unique = true)
    private String isbn;  // ISBN-13

    @Column(nullable = false)
    private String shelfLocation; // A1-B2

    @Column(nullable = false)
    private Integer totalCopies = 1;

    @Column(nullable = false)
    private Integer availableCopies = 1;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

    private Boolean isDeleted = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
  
    public enum Status {
        AVAILABLE,
        NOT_AVAILABLE;

    	 @JsonCreator
         public static Status from(String value) {
             if (value == null) return null;
             return Status.valueOf(value.toUpperCase());
        }
}
}