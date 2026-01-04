package com.campusFacilities.www.model.Library;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private String title;

    private String author;

    @Column(unique = true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private BookCategory category;

    private String publisher;

    private String edition;

    private Integer totalCopies = 1;

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
            if (value == null) {
                return null; 
            }
            try {
                return Status.valueOf(value.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value: " + value);
            }
        }
}
}