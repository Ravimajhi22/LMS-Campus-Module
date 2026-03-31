package com.campusFacilities.www.model.Library;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Books {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "book_id")
	    private Long id;

	    @Column(nullable = false)
	    private String title;

	    @Column(nullable = false)
	    private String author;

	    @Column(name = "publisher")
	    private String publisher;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "category_id", nullable = false)
	    private BookCategory category;

	    @Column(name = "edition")
	    private String edition;

	    @Column(name = "year", length = 4)
	    private String year;

	    @Column(name = "language")
	    private String language;

	    @Column(name = "access_url")
	    private String accessUrl;

	    @Column(name = "format")
	    private String format;

	    @Column(name = "digital_type")
	    private String digitalType;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private BookType type = BookType.PHYSICAL;

	    @Column(nullable = true, unique = true)
	    private String isbn;

	    @Column(name = "shelf_location", nullable = true)
	    private String shelfLocation;

	    @Column(name = "total_copies", nullable = false)
	    private Integer totalCopies;

	    @Column(name = "available_copies", nullable = false)
	    private Integer availableCopies;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Status status = Status.AVAILABLE;

	    @Column(name = "is_deleted", nullable = false)
	    private Boolean isDeleted = false;

	    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnoreProperties("book")
	    private List<BookBarcode> barcodes = new ArrayList<>();

	    @Column(name = "created_at", nullable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at", nullable = false)
	    private LocalDateTime updatedAt;

	    @PrePersist
	    protected void onCreate() {
	        createdAt = LocalDateTime.now();
	        updatedAt = LocalDateTime.now();
	        if (totalCopies == null)
	            totalCopies = 1;
	        if (availableCopies == null)
	            availableCopies = totalCopies;
	        if (status == null)
	            status = Status.AVAILABLE;
	        if (isDeleted == null)
	            isDeleted = false;
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        updatedAt = LocalDateTime.now();
	    }

	    public enum BookType {
	        PHYSICAL,
	        DIGITAL
	    }

	    public enum Status {
	        AVAILABLE,
	        UNAVAILABLE
	    }
	}
