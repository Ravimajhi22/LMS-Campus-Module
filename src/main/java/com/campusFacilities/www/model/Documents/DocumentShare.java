package com.campusFacilities.www.model.Documents;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "document_shares")
@Data
public class DocumentShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareId;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

	
	  @ManyToOne
	  
	  @JoinColumn(name = "shared_with", nullable = false) private User sharedWith;
	  
	  @ManyToOne
	  
	  @JoinColumn(name = "shared_by", nullable = false) private User sharedBy;
	 

    private LocalDate expiryDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

